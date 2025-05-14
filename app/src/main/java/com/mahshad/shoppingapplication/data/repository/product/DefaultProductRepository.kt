package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.database.ProductEntity
import com.mahshad.shoppingapplication.data.datasource.local.product.ProductLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {
    val flowable1 = Flowable.just(1)
    val flowable2 = Flowable.just(2)
    fun test(): Flowable<String> {
        return Flowable.combineLatest(
            flowable1, flowable2
        ) { a, b ->
            "${a + b}"

        }
    }

    override fun getProducts(): Flowable<List<Product>> {
        return Flowable.combineLatest(
            remoteDataSource.getProducts().toFlowable(),
            localDataSource.getAllProduct(),
        ) { response: Response<List<ProductDTO>>, productEntities: List<ProductEntity> ->
            if (response.isSuccessful) {
                response.body()?.let {
                    it.map { productDto ->
                        val isFavorite =
                            productEntities.any { productEntity -> productEntity.id == productDto.id }
                        Product(
                            category = productDto.category,
                            description = productDto.description,
                            id = productDto.id,
                            image = productDto.image,
                            price = productDto.price,
                            isFavorite = isFavorite,
                            ratingDTO = productDto.ratingDTO,
                            title = productDto.title
                        )
                    }

                }
            } else {

            }

            emptyList<Product>()

        }

    }

    override fun getFavoriteProducts(): Flowable<List<ProductEntity>> =
        localDataSource.getAllProduct()

    override fun insert(product: ProductEntity): Completable = localDataSource.insert(product)

    override fun delete(productId: Int): Completable = localDataSource.delete(productId)

}