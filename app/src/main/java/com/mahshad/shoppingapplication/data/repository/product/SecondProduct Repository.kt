package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.datasource.local.product.DefaultProductLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.NetworkRemoteDataSource
import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.models.Rating
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import com.mahshad.shoppingapplication.di.ComputationScheduler
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class SecondProductRepository @Inject constructor
    (
    val productLocalDataSource: DefaultProductLocalDataSource,
    val productRemoteDataSource: NetworkRemoteDataSource,
    @ComputationScheduler val computationScheduler: Scheduler
) : ProductRepository {
    /**
     * this is the product repository producing the list of products out of the database, network,
     * and their combination.
     */
    override fun getModifiedProducts(): Flowable<List<Product>> {
        /**
         * getting the products from the database and the server,
         * create the updated list of products
         */
        return Flowable.combineLatest(
            productRemoteDataSource.getProducts().toFlowable(),
            productLocalDataSource.getAllProduct()
        ) { listProductDTOs, listProductEntities ->
            if (listProductDTOs.isSuccessful) {
                val productDtos = listProductDTOs.body()
                productDtos?.let {
                    productDtos.map { productDto ->
                        val isFavorite = listProductEntities.any { productDto.id == it.id }
                        Product(
                            category = productDto.category,
                            description = productDto.description,
                            id = productDto.id,
                            image = productDto.image,
                            price = productDto.price,
                            rating = Rating(
                                productDto.ratingDTO?.count,
                                productDto.ratingDTO?.rate
                            ),
                            title = productDto.title,
                            isFavorite = isFavorite
                        )

                    }
                } ?: emptyList<Product>()
            } else {
                emptyList<Product>()
            }

        }.subscribeOn(computationScheduler)
    }

    override fun getProducts(): Single<List<Product>> {
        val singleProductDtos: Single<retrofit2.Response<List<ProductDTO>>> =
            productRemoteDataSource.getProducts()
        return singleProductDtos.map { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    it.map {
                        Product(
                            category = it.category,
                            description = it.description,
                            id = it.id,
                            image = it.image,
                            price = it.price,
                            rating = Rating(
                                it.ratingDTO?.count,
                                it.ratingDTO?.rate
                            ),
                            title = it.title
                        )
                    }
                } ?: emptyList<Product>()
            } else {
                emptyList<Product>()
            }
        }.subscribeOn(computationScheduler)
    }

    override fun getFavoriteProducts(): Flowable<List<Product>> {
        return productLocalDataSource.getAllProduct().map { productEntities ->
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
}