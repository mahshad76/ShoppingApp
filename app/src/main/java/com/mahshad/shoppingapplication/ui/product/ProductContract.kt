package com.mahshad.shoppingapplication.ui.product

import com.mahshad.shoppingapplication.common.BasePresenter
import com.mahshad.shoppingapplication.common.BaseView
import com.mahshad.shoppingapplication.data.models.Product

interface ProductContract {
    interface View : BaseView {
        fun showErrorMessage(message: String)
        fun showLoading()
        fun hideLoading()
        fun navigateToProductDetail()
        fun showProducts(products: List<Product>)
        fun showModifiedProducts(products: List<Product>)
        fun showBookmarkSuccessMessage()
        fun showUnBookmarkSuccessMessage()
    }

    interface Presenter : BasePresenter<View> {
        fun getModifiedProducts()
        fun getProducts()
        fun bookmarkProduct(product: Product)
        fun unBookmarkProduct(productId: Int)
    }

}