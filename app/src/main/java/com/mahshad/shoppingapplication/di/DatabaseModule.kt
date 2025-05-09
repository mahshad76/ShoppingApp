package com.mahshad.shoppingapplication.di

import android.content.Context
import androidx.room.Room
import com.mahshad.shoppingapplication.data.database.AppDatabase
import com.mahshad.shoppingapplication.data.database.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

}