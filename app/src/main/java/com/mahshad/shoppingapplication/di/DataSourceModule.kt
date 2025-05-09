package com.mahshad.shoppingapplication.di

import com.mahshad.shoppingapplication.data.datasource.local.product.DefaultProductLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.local.product.ProductLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.local.user.SharedPrefUserLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.local.user.UserLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.NetworkRemoteDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(networkRemoteDataSource: NetworkRemoteDataSource): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(sharedPrefLocalDataSource: SharedPrefUserLocalDataSource): UserLocalDataSource

    @Binds
    abstract fun bindProductLocalDataSource(defaultProductLocalDataSource: DefaultProductLocalDataSource): ProductLocalDataSource
}