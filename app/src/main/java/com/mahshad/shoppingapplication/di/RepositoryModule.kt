package com.mahshad.shoppingapplication.di

import com.mahshad.shoppingapplication.data.repository.category.CategoryRepository
import com.mahshad.shoppingapplication.data.repository.category.DefaultCategoryRepository
import com.mahshad.shoppingapplication.data.repository.product.DefaultProductRepository
import com.mahshad.shoppingapplication.data.repository.product.ProductRepository
import com.mahshad.shoppingapplication.data.repository.user.DefaultUserRepository
import com.mahshad.shoppingapplication.data.repository.user.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(defaultUserRepository: DefaultUserRepository): UserRepository

    @Binds
    abstract fun bindCategoryRepository(defaultCategoryRepository: DefaultCategoryRepository): CategoryRepository

    @Binds
    abstract fun bindProductRepository(defaultProductRepository: DefaultProductRepository): ProductRepository

}