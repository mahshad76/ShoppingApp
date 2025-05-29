package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.models.Product
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ProductRepository {
    fun getModifiedProducts(): Flowable<List<Product>>
    fun getProducts(): Single<List<Product>>
    fun getFavoriteProducts(): Flowable<List<Product>>
    fun bookmarkProduct(product: Product): Completable
}