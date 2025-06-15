package com.mahshad.shoppingapplication.ui.localProducts

import com.mahshad.shoppingapplication.data.models.Product

interface Contract {
    interface View {
        fun showErrorMessage(message: String)
        fun showLoading()
        fun hideLoading()
        fun showProducts(response: List<Product>)
        fun notifyDataUpdate(update: List<Product>)
    }

    interface Presenter {
        fun getProducts()
        fun onAttach(view: View)
        fun onDetach()
        fun onDestroy()
        fun updateData(product: Product, position: Int)
    }
}