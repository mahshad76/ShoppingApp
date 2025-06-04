package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.database.ProductEntity
import com.mahshad.shoppingapplication.data.datasource.local.product.ProductLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.models.Rating
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import com.mahshad.shoppingapplication.di.ComputationScheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ProductLocalDataSource,
    @ComputationScheduler val computationScheduler: Scheduler
) : ProductRepository {

    override fun getModifiedProducts(): Flowable<List<Product>> {
        return Flowable.combineLatest(
            remoteDataSource.getProducts().toFlowable(),
            localDataSource.getAllProduct(),
        ) { response: Response<List<ProductDTO>>, productEntities: List<ProductEntity> ->
            if (response.isSuccessful) {
                response.body()?.let { productDtos ->
                    productDtos.map { productDto ->
                        val isFavorite =
                            productEntities.any { productEntity -> productEntity.id == productDto.id }
                        Product(
                            category = productDto.category,
                            description = productDto.description,
                            id = productDto.id,
                            image = productDto.image,
                            price = productDto.price,
                            isFavorite = isFavorite,
                            rating = Rating(
                                productDto.ratingDTO?.count,
                                productDto.ratingDTO?.rate
                            ),
                            title = productDto.title
                        )
                    }
                } ?: emptyList()

            } else {
                emptyList()
            }
        }.subscribeOn(computationScheduler)
    }

    override fun getProducts(): Single<List<Product>> {
        return remoteDataSource.getProducts().map { response ->
            if (response.isSuccessful) {
                response.body()?.let { productDTOS ->
                    productDTOS.map { productDTO ->
                        Product(
                            category = productDTO.category,
                            description = productDTO.description,
                            id = productDTO.id,
                            image = productDTO.image,
                            price = productDTO.price,
                            rating = Rating(
                                productDTO.ratingDTO?.count,
                                productDTO.ratingDTO?.rate
                            ),
                            title = productDTO.title
                        )

                    }
                } ?: emptyList()
            } else {
                emptyList()
            }
        }.subscribeOn(computationScheduler)
    }

    override fun getFavoriteProducts(): Flowable<List<Product>> {
        return localDataSource.getAllProduct().map { productEntities ->
            productEntities.map { productEntity ->
                Product(
                    category = null,
                    description = productEntity.description,
                    id = productEntity.id,
                    image = productEntity.image,
                    price = productEntity.price,
                    rating = Rating(
                        count = null,
                        rate = null
                    ),
                    title = null,
                    isFavorite = true
                )

            }
        }.subscribeOn(computationScheduler)
    }

    override fun bookmarkProduct(product: Product): Completable {
        return product.let {
            ProductEntity(
                description = it.description,
                id = it.id,
                image = it.image,
                price = it.price
            )
        }.let { productEntity ->
            localDataSource.insert(productEntity)
        }
    }

    override fun unBookmarkProduct(productId: Int): Completable {
        return localDataSource.delete(productId)
    }


}