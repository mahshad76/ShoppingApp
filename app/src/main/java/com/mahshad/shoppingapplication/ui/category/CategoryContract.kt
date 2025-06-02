package com.mahshad.shoppingapplication.ui.category

import com.mahshad.shoppingapplication.common.BasePresenter
import com.mahshad.shoppingapplication.common.BaseView

interface CategoryContract {
    interface View : BaseView {
        fun showErrorMessage(message: String)
        fun showLoading()
        fun hideLoading()
        fun showCategories(categories: List<String>?)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategories()
    }
}