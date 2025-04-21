package com.mahshad.shoppingapplication.network

import com.mahshad.shoppingapplication.dto.ProductsItem
import retrofit2.http.GET

//api of category and products
interface ApiService {
    @GET("products")
    fun getProducts(): retrofit2.Call<List<ProductsItem>>

    @GET("products/categories")
    fun getCategories(): retrofit2.Call<List<String>>
}