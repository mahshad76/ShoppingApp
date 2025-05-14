package com.mahshad.shoppingapplication.data.repository.product

import com.mahshad.shoppingapplication.data.models.Product
import io.reactivex.Flowable

interface ProductRepository {
    fun getProducts(): Flowable<List<Product>>
//    fun getFavoriteProducts(): Flowable<List<ProductEntity>>
//    fun insert(product: ProductEntity): Completable
//    fun delete(productId: Int): Completable

}