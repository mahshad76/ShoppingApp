package com.mahshad.shoppingapplication.data.repository.category

import io.reactivex.Single
import retrofit2.Response

interface CategoryRepository {
    fun getCategories(): Single<Response<List<String>>>
}