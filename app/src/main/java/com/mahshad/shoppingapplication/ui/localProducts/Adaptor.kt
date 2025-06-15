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

class ProductAdapter(
    private val clickListener: ClickListener
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, clickListener)
    }

    class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
        private val title = productView.findViewById<TextView>(R.id.product_category)
        private val rate = productView.findViewById<TextView>(R.id.product_rate)
        private val isFavorite = productView.findViewById<ImageView>(R.id.favorite_icon)
        private val productImage = productView.findViewById<ImageView>(R.id.myImageView)

        fun bind(product: Product, clickListener: ClickListener) {
            title.text = product.category
            rate.text = product.rating?.rate.toString()
            isFavorite.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    itemView.context,
                    if (product.isFavorite) R.color.red_heart else R.color.black
                )
            )

            Glide.with(itemView.context)
                .load(product.image)
                .into(productImage)

            isFavorite.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onClick(product, position)
                }
            }

        }
    }
}

interface ClickListener {
    fun onClick(product: Product, position: Int)
}
