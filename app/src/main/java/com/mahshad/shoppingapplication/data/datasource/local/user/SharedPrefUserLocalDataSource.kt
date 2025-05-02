package com.mahshad.shoppingapplication.data.datasource.local.user

import android.content.Context
import androidx.core.content.edit
import com.mahshad.shoppingapplication.di.IoScheduler
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import javax.inject.Inject

const val USER_INFO = "user_info"

class SharedPrefUserLocalDataSource @Inject constructor(
    context: Context,
    @IoScheduler private val ioScheduler: Scheduler
) : UserLocalDataSource {
    private val sharedPref = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)

    override fun saveUser(key: String, str: String): Completable {
        return Completable.fromAction {
            sharedPref.edit { putString(key, str) }
        }.subscribeOn(ioScheduler)
    }

    override fun getUser(key: String): Maybe<String?> {
        return Maybe.fromCallable {
            sharedPref.getString(key, "")
        }.filter { it.isNotEmpty() }
            .subscribeOn(ioScheduler)
    }

    override fun clearUser(key: String): Completable {
        return Completable.fromAction {
            sharedPref.edit { remove(key) }
        }.subscribeOn(ioScheduler)
    }
}