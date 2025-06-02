package com.mahshad.shoppingapplication.ui.category

import android.util.Log
import com.mahshad.shoppingapplication.data.repository.category.DefaultCategoryRepository
import com.mahshad.shoppingapplication.di.IoScheduler
import com.mahshad.shoppingapplication.di.MainScheduler
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import jakarta.inject.Inject
import retrofit2.Response

class CategoryPresenter @Inject constructor(
    private val remoteRepository:
    DefaultCategoryRepository, @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : CategoryContract.Presenter {
    private var view: CategoryContract.View? = null
    private val disposables = CompositeDisposable()
    override fun getCategories() {
        view?.showLoading()
        val disposable: Disposable = remoteRepository
            .getCategories()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                { response: Response<List<String>> ->
                    if (response.isSuccessful) {
                        view?.let { nonNullView: CategoryContract.View ->
                            nonNullView.hideLoading()
                            nonNullView.showCategories(response.body())
                        }
                    } else {
                        Log.e("TAG", "getCategories")
                    }
                },
                { e: Throwable ->
                    println("Sth went wrong: ${e.message}")
                })
        disposables.add(disposable)
    }

    override fun attachView(view: CategoryContract.View) {
        this.view = view
    }

    override fun detachView(view: CategoryContract.View) {
        TODO("Not yet implemented")
    }

    override fun destroy() {
    }
}