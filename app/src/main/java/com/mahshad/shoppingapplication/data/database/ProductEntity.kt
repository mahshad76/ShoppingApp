package com.mahshad.shoppingapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val identifier: Int,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "price") val price: Double?,
)