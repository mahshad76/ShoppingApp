package com.mahshad.shoppingapplication.data.models

data class Product(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val rating: Rating?,
    val title: String?,
    var isFavorite: Boolean = false
) {
    companion object {
        val DEFAULT = Product(
            category = "women",
            description = "teenagers",
            id = 1,
            image = null,
            price = null,
            rating = null,
            title = null
        )
    }
}
