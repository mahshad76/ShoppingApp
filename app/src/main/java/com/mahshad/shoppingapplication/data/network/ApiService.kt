package com.mahshad.shoppingapplication.data.network

import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Single<Response<List<ProductDTO>>>

    @GET("products/categories")
    fun getCategories(): Single<Response<List<String>>>
}