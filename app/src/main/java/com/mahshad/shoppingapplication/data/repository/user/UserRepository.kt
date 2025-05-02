package com.mahshad.shoppingapplication.data.repository.user

import io.reactivex.Completable
import io.reactivex.Maybe

interface UserRepository {
    fun saveUser(key: String, str: String): Completable

    fun getUser(key: String): Maybe<String?>

    fun clearUser(key: String): Completable
}