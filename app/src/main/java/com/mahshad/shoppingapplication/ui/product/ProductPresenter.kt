package com.mahshad.shoppingapplication.ui.product

import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.repository.product.ProductRepository
import com.mahshad.shoppingapplication.di.IoScheduler
import com.mahshad.shoppingapplication.di.MainScheduler
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import jakarta.inject.Inject

class ProductPresenter @Inject constructor(
    private val productRepository: ProductRepository,
    private val compositeDisposable: CompositeDisposable,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : ProductContract.Presenter {

    private var view: ProductContract.View? = null

    override fun getModifiedProducts() {
        view?.showLoading()
        compositeDisposable.add(
            productRepository.getModifiedProducts()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe({ products ->
                    view?.apply {
                        hideLoading()
                        showModifiedProducts(products)
                    }
                }, { error ->
                    view?.apply {
                        hideLoading()
                        showErrorMessage(error.message ?: "Unknown error")
                    }
                })
        )
    }

    override fun getProducts() {
        view?.showLoading()
        compositeDisposable.add(
            productRepository.getProducts()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe({ products ->
                    view?.apply {
                        hideLoading()
                        showProducts(products)
                    }
                }, { error ->
                    view?.apply {
                        hideLoading()
                        showErrorMessage(error.message ?: "Unknown error")
                    }
                })
        )
    }

    override fun bookmarkProduct(product: Product) {
        compositeDisposable.add(
            productRepository.bookmarkProduct(product)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    view?.showBookmarkSuccessMessage()
                }, { error ->
                    view?.showErrorMessage(error.message ?: "Unknown error")
                })
        )
    }

    override fun unBookmarkProduct(productId: Int) {
        compositeDisposable.add(
            productRepository.unBookmarkProduct(productId)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe({
                    view?.showUnBookmarkSuccessMessage()
                }, { error ->
                    view?.showErrorMessage(error.message ?: "Unknown error")
                })
        )

    }

    override fun attachView(view: ProductContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

}