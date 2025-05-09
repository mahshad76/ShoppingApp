package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.database.ProductEntity
import com.mahshad.shoppingapplication.data.datasource.local.product.ProductLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {

    override fun getProducts(): Flowable<Response<List<ProductDTO>>> =
        remoteDataSource.getProducts()

    override fun getFavoriteProducts(): Flowable<List<ProductEntity>> =
        localDataSource.getAllProduct()

}