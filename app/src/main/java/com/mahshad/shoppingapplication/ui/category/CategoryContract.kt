package com.mahshad.shoppingapplication.ui.category

import com.mahshad.shoppingapplication.common.BasePresenter
import com.mahshad.shoppingapplication.common.BaseView
import io.reactivex.Single
import retrofit2.Response

interface CategoryContract {
    interface View : BaseView {
        fun showErrorMessage(message: String)
        fun showLoading()
        fun hideLoading()
        fun showCategories(categories: Single<Response<List<String>>>)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategories(): Single<Response<List<String>>>
    }
}