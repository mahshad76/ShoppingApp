package com.mahshad.shoppingapplication.di

import android.content.Context
import com.mahshad.shoppingapplication.ui.MainActivity
import com.mahshad.shoppingapplication.ui.category.CategoriesFragment
import com.mahshad.shoppingapplication.ui.localProducts.LocalProductFragment
import com.mahshad.shoppingapplication.ui.product.ProductFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ThreadingModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ProductPresenterModule::class,
        CategoryPresenterModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: ProductFragment)
    fun inject(fragment: CategoriesFragment)
    fun inject(fragment: LocalProductFragment)
}