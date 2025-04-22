package com.mahshad.shoppingapplication.network

import com.mahshad.shoppingapplication.dto.Product
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

//api of category and products
interface ApiService {
    @GET("products")
    fun getProducts(): Single<Response<List<Product>>>

    @GET("products/categories")
    fun getCategories(): Single<Response<List<String>>>
}