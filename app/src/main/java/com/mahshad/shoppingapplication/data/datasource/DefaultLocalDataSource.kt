package com.mahshad.shoppingapplication.data.datasource

import android.content.Context
import androidx.core.content.edit
import com.mahshad.shoppingapplication.data.datasource.local.user.UserLocalDataSource
import com.mahshad.shoppingapplication.di.IoScheduler
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import javax.inject.Inject

class DefaultLocalDataSourceSource @Inject constructor(
    private val context: Context,
    @IoScheduler val ioScheduler: Scheduler
) : UserLocalDataSource {
    private val sharedPreference = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    override fun saveUser(key: String, str: String): Completable {
        return Completable.fromAction {
            sharedPreference.edit() { putString(key, str) }
        }.subscribeOn(ioScheduler)
    }

    fun saveUser2(key: String, str: String): Completable {
        return Completable.create { emitter ->
            try {
                sharedPreference.edit { putString(key, str) }
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }

        }
    }

    //fromCallable returns a Maybe object is defined lazily and its callable is executed as soon as it is subscribed to.
    //the filter operator works on the items emitted by an Observable
    override fun getUser(key: String): Maybe<String?> {
        return Maybe.fromCallable {
            sharedPreference.getString(key, "")
        }.filter { it.isNotEmpty() }
    }

    fun getUser2(key: String): Maybe<String?> {
        return Maybe.create { emitter ->
            try {
                val str = sharedPreference.getString(key, "")
                if (str.isNullOrEmpty()) {
                    emitter.onComplete()
                } else {
                    emitter.onSuccess(str)
                }

            } catch (e: Exception) {
                emitter.onError(e)
            }

        }
    }

    override fun clearUser(key: String): Completable {
        return Completable.fromAction {
            sharedPreference.edit { remove(key) }
        }
    }

}