package com.mahshad.shoppingapplication.data.datasource.remote

import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Single
import retrofit2.Response

interface RemoteDataSource {
    fun getProducts(): Single<Response<List<ProductDTO>>>
    fun getCategories(): Single<Response<List<String>>>
}