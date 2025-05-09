package com.mahshad.shoppingapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(product: List<ProductEntity>): Completable

    @Query("select * from product_table")
    fun getAllProduct(): Flowable<List<ProductEntity>>

    @Query("DELETE FROM product_table")
    fun deleteAll(): Completable
}