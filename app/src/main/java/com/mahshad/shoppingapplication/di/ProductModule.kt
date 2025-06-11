package com.mahshad.shoppingapplication.di

import com.mahshad.shoppingapplication.ui.localProducts.Contract
import com.mahshad.shoppingapplication.ui.localProducts.Presenter
import com.mahshad.shoppingapplication.ui.product.ProductContract
import com.mahshad.shoppingapplication.ui.product.ProductPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class ProductPresenterModule {
    @Binds
    abstract fun bindProductPresenter(defaultProductPresenter: ProductPresenter): ProductContract.Presenter

    @Binds
    abstract fun bindLocalProductPresenter(defaultPresenter: Presenter): Contract.Presenter
}