package com.mahshad.shoppingapplication.ui.product

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product

class ProductAdaptor(
    private val context: Context, private val productList: List<Product>,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.product_layout,
            parent, false
        )
        return ProductViewHolder(viewLayout)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.title.text = productList[position].category
        holder.rate.text = productList[position].rating?.rate.toString()
        holder.isFavorite.imageTintList = if (productList[position].isFavorite) {
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red_heart))
        } else {
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.black))
        }
        holder.productCard.setOnClickListener { clickListener.onItemClick(productList[position]) }
        holder.isFavorite.setOnClickListener { clickListener.onItemClick(productList[position]) }
        Glide.with(context)
            .load(productList[position].image)
            .into(holder.productImage)

    }

}

//TODO: add binding
class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
    val productCard = productView.findViewById<CardView>(R.id.productCard)
    val title = productView.findViewById<TextView>(R.id.product_category)
    val rate = productView.findViewById<TextView>(R.id.product_rate)
    val isFavorite = productView.findViewById<ImageView>(R.id.favorite_icon)
    val productImage = productView.findViewById<ImageView>(R.id.myImageView)
}

interface OnItemClickListener {
    ///give the position as input if I want to move to the detail activity
    fun onItemClick(productItem: Product)
}