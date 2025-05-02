package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Single
import retrofit2.Response

interface ProductRepository {
    fun getProducts(): Single<Response<List<ProductDTO>>>
}