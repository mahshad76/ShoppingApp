package com.mahshad.shoppingapplication.data.repository.category

import com.mahshad.shoppingapplication.data.datasource.remote.RemoteDataSource
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DefaultCategoryRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CategoryRepository {
    override fun getCategories(): Single<Response<List<String>>> {
        return remoteDataSource.getCategories()
    }
}