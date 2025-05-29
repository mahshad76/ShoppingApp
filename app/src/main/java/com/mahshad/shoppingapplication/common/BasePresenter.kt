package com.mahshad.shoppingapplication.common

interface BasePresenter<in V : BaseView> {
    fun attachView(view: V)
    fun detachView(view: V)
    fun destroy()
}