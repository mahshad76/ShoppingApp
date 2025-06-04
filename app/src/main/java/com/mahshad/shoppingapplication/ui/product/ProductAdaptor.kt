package com.mahshad.shoppingapplication.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product

class ProductAdaptor(private val context: Context, private val productList: List<Product>) :
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
        /*holder.category.text = productList[position].category
        holder.likeButton.isChecked = productList[position].isFavorite*/
    }

}

//TODO: add binding
class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
    val title = productView.findViewById<TextView>(R.id.product_category)
    val rate = productView.findViewById<TextView>(R.id.product_rate)
    /*val category = productView.findViewById<TextView>(R.id.product_category)
    val likeButton = productView.findViewById<ToggleButton>(R.id.item_toggle)*/
}