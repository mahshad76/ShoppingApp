package com.mahshad.shoppingapplication.data.datasource.local.user

import io.reactivex.Completable
import io.reactivex.Maybe

interface UserLocalDataSource {

    fun saveUser(key: String, str: String): Completable

    fun getUser(key: String): Maybe<String?>

    fun clearUser(key: String): Completable
}