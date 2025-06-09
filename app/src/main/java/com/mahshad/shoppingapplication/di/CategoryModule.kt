package com.mahshad.shoppingapplication.di

import com.mahshad.shoppingapplication.ui.category.CategoryContract
import com.mahshad.shoppingapplication.ui.category.CategoryPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class CategoryPresenterModule {
    @Binds
    abstract fun bindCategoryPresenter(defaultCategoryPresenter: CategoryPresenter): CategoryContract.Presenter
}