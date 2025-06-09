package com.mahshad.shoppingapplication.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mahshad.shoppingapplication.R

class CategoriesAdapter(private val categoriesList: List<String>) : Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.category_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categoriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = categoriesList[position]
    }
}

class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.categoryTextView)
}