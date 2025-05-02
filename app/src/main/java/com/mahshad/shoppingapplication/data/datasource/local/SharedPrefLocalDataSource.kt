package com.mahshad.shoppingapplication.data.datasource.local

import android.content.Context
import androidx.core.content.edit
import com.mahshad.shoppingapplication.di.IoScheduler
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import javax.inject.Inject

private const val USER_INFO = "user_info"

class SharedPrefLocalDataSource @Inject constructor(
    context: Context,
    @IoScheduler private val ioScheduler: Scheduler
) : LocalDataSource {
    private val sharedPref = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)

    override fun saveUser(key: String, str: String): Completable {
        //fromCallable
        return Completable.create { emitter ->
            try {
                sharedPref.edit { putString(key, str) }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(ioScheduler)
    }

    override fun getPassword(key: String): Maybe<String?> {
        return Maybe.create { emitter ->
            emitter.onSuccess(sharedPref.getString(key, ""))
            //emitter.onError()
            sharedPref.getString(key, "")
        }.filter { it.isNotEmpty() }
            .subscribeOn(ioScheduler)
    }

    override fun clearUser(key: String): Completable {
        return Completable.create { emitter ->
            sharedPref.edit { remove(key) }
        }.subscribeOn(ioScheduler)
    }
}