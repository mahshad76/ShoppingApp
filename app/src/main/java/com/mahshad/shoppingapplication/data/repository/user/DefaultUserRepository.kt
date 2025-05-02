package com.mahshad.shoppingapplication.data.repository.user

import com.mahshad.shoppingapplication.data.datasource.local.user.UserLocalDataSource
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource) :
    UserRepository {
    override fun saveUser(key: String, str: String): Completable {
        return userLocalDataSource.saveUser(key, str)
    }

    override fun getUser(key: String): Maybe<String?> {
        return userLocalDataSource.getUser(key)
    }

    override fun clearUser(key: String): Completable {
        return userLocalDataSource.clearUser(key)
    }
}