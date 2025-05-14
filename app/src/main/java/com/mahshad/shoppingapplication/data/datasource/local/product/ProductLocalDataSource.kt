package com.mahshad.shoppingapplication.data.datasource.local.product

import com.mahshad.shoppingapplication.data.database.ProductEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProductLocalDataSource {
    fun insertAll(product: List<ProductEntity>): Completable

    fun getAllProduct(): Flowable<List<ProductEntity>>

    fun insert(product: ProductEntity): Completable

    fun delete(productId: Int): Completable

    fun deleteAll(): Completable
}