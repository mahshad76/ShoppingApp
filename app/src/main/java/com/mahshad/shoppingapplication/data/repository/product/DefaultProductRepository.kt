package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ProductRepository {

    override fun getProducts(): Single<Response<List<ProductDTO>>> = remoteDataSource.getProducts()

}