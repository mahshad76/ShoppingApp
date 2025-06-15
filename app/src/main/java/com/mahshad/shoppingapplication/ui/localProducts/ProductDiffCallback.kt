package com.mahshad.shoppingapplication.ui.localProducts

// ProductDiffCallback.kt
import androidx.recyclerview.widget.DiffUtil
import com.mahshad.shoppingapplication.data.models.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

    override fun getChangePayload(oldItem: Product, newItem: Product) = if (oldItem.isFavorite !=
        newItem.isFavorite
    ) true else null
}