package com.mahshad.shoppingapplication.ui.localProducts

import com.mahshad.shoppingapplication.data.models.Product
import io.reactivex.Single

interface Contract {
    interface View {
        fun showError()
        fun showLoading()
        fun showProducts()
    }

    interface Presenter {
        fun getProducts(): Single<List<Product>>
        fun onAttach()
        fun onDetach()
        fun onDestroy()
    }
}