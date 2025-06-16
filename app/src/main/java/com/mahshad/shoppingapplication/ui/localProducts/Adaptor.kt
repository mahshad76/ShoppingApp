package com.mahshad.shoppingapplication.ui.localProducts

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product


class ProductAdapter(private val listener: ClickListener) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.product_category)
        private val rate = itemView.findViewById<TextView>(R.id.product_rate)
        private val isFavorite = itemView.findViewById<ImageView>(R.id.favorite_icon)
        private val productImage = itemView.findViewById<ImageView>(R.id.myImageView)

        fun bind(product: Product) {
            title.text = product.category
            rate.text = product.rating?.rate.toString()
            isFavorite.imageTintList = if (product.isFavorite) {
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.red_heart
                    )
                )
            } else {
                ColorStateList.valueOf(ContextCompat.getColor(itemView.context, R.color.black))
            }
            Glide.with(itemView.context)
                .load(product.image)
                .into(productImage)
            isFavorite.setOnClickListener { product.id?.let { it1 -> listener.clickListener(it1) } }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_layout, parent,
                false
            )
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface ClickListener {
    fun clickListener(productId: Int)
}

