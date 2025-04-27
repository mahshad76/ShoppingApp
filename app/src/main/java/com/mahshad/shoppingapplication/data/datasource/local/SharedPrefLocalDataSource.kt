package com.mahshad.shoppingapplication.data.datasource.local

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

///There are just three categories so
class SharedPrefLocalDataSource @Inject constructor(context: Context) : LocalDataSource {
    private val sharedPref = context.getSharedPreferences("users_info", Context.MODE_PRIVATE)

    override fun saveUser(key: String, str: String) {
        sharedPref.edit { putString(key, str) }
    }

    override fun getPassword(key: String): String {
        return sharedPref.getString(key, null) ?: ""
    }

    override fun clearUser(key: String) {
        sharedPref.edit { remove(key) }
    }
}