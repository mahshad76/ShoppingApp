package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.database.ProductEntity
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import io.reactivex.Flowable
import retrofit2.Response

interface ProductRepository {
    fun getProducts(): Flowable<Response<List<ProductDTO>>>
    fun getFavoriteProducts(): Flowable<List<ProductEntity>>
}