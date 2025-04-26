package com.mahshad.shoppingapplication.data.datasource.local

/*
Keeping the username and password in a shared preference file for the shopping application.
 */
interface LocalDataSource {

    fun saveUser(key: String, str: String)

    fun getPassword(key: String): String

    fun clearUser(key: String)
}