package com.mahshad.shoppingapplication.data.datasource.local

import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * Interface for local data source operations.
 *
 * This interface defines methods for interacting with local storage, such as saving,
 * retrieving, and clearing user-related data.
 */
interface LocalDataSource {
    /**
     * Saves user data associated with the specified key.
     *
     * @param key The key under which the user data should be stored.
     * @param str The user data string to be saved.
     * @return A Completable that completes when the data has been saved successfully or
     *         fails with an error if the operation encounters an issue.
     */
    fun saveUser(key: String, str: String): Completable

    /**
     * Retrieves the password associated with the specified key.
     *
     * @param key The key associated with the password to be retrieved.
     * @return A Maybe emitting the password string if found, or completing empty if not found.
     */
    fun getPassword(key: String): Maybe<String?>

    fun clearUser(key: String): Completable
}