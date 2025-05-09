package com.mahshad.shoppingapplication.data.models

import com.mahshad.shoppingapplication.data.models.response.RatingDTO

data class Product(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val ratingDTO: RatingDTO?,
    val title: String?,
    var isFavorite: Boolean = false
)
