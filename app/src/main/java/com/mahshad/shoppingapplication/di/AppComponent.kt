package com.mahshad.shoppingapplication.di

import android.content.Context
import com.mahshad.shoppingapplication.ui.mainActivity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ThreadingModule::class, DataSourceModule::class, RepositoryModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}