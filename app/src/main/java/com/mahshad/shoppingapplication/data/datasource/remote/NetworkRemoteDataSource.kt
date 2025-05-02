package com.mahshad.shoppingapplication.data.datasource.remote

import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import com.mahshad.shoppingapplication.data.network.ApiService
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class NetworkRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    override fun getProducts(): Single<Response<List<ProductDTO>>> {
        return apiService.getProducts()
    }

    override fun getCategories(): Single<Response<List<String>>> {
        return apiService.getCategories()
    }
}