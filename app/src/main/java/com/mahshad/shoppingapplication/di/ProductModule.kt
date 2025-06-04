package com.mahshad.shoppingapplication.di

import com.mahshad.shoppingapplication.ui.product.ProductContract
import com.mahshad.shoppingapplication.ui.product.ProductPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ProductPresenterModule {
    @Binds
    abstract fun bindProductPresenter(defaultProductPresenter: ProductPresenter): ProductContract.Presenter
}