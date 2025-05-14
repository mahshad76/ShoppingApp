package com.mahshad.shoppingapplication.data.datasource.local.product

import com.mahshad.shoppingapplication.data.database.ProductDao
import com.mahshad.shoppingapplication.data.database.ProductEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class DefaultProductLocalDataSource @Inject constructor(private val productDao: ProductDao) :
    ProductLocalDataSource {
    override fun insertAll(product: List<ProductEntity>): Completable =
        productDao.insertAll(product)

    override fun getAllProduct(): Flowable<List<ProductEntity>> = productDao.getAllProduct()

    override fun insert(product: ProductEntity) = productDao.insert(product)

    override fun delete(productId: Int) = productDao.delete(productId)

    override fun deleteAll(): Completable = productDao.deleteAll()
}