package com.mahshad.shoppingapplication.di

import com.mahshad.shoppingapplication.data.datasource.local.LocalDataSource
import com.mahshad.shoppingapplication.data.datasource.local.SharedPrefLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.NetworkRemoteDataSource
import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindRemoteDataSource(networkRemoteDataSource: NetworkRemoteDataSource): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(sharedPrefLocalDataSource: SharedPrefLocalDataSource): LocalDataSource
}