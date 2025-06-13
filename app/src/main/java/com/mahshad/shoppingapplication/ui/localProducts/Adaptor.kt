package com.mahshad.shoppingapplication.ui.localProducts

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product

class Adaptor(
    private val productList: List<Product>, private val clickListener: (Product) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.product_layout,
            parent, false
        )
        val viewObject = ViewHolder(viewLayout)
        viewObject.isFavorite.setOnClickListener {
            val position = viewObject.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clickListener(productList[position])
            }
        }
        return viewObject
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = productList[position].category
        holder.rate.text = productList[position].rating?.rate.toString()
        holder.isFavorite.imageTintList = if (productList[position].isFavorite) {
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red_heart
                )
            )
        } else {
            ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.black))
        }
//        holder.isFavorite.setOnClickListener {
//            productList[position].isFavorite = !productList[position].isFavorite
//            notifyItemChanged(position)
        // }
        Glide.with(holder.itemView.context)
            .load(productList[position].image)
            .into(holder.productImage)

    }

}

class ViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
    val title = productView.findViewById<TextView>(R.id.product_category)
    val rate = productView.findViewById<TextView>(R.id.product_rate)
    val isFavorite = productView.findViewById<ImageView>(R.id.favorite_icon)
    val productImage = productView.findViewById<ImageView>(R.id.myImageView)
}
