package com.mahshad.shoppingapplication.ui.localProducts

import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.repository.product.ProductRepository
import com.mahshad.shoppingapplication.di.ComputationScheduler
import com.mahshad.shoppingapplication.di.MainScheduler
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class Presenter @Inject constructor(
    private val productRepository: ProductRepository,
    @ComputationScheduler private val computationScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : Contract.Presenter {
    private var view: Contract.View? = null
    private var disposableList: MutableList<Disposable> = mutableListOf()
    private var cachedProducts: MutableList<Product>? = null

    override fun getProducts() {
        view?.showLoading()
        view?.let { nonNullView ->
            productRepository.getProducts()
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe(object : SingleObserver<List<Product>> {
                    override fun onSubscribe(d: Disposable) {
                        disposableList.add(d)
                    }

                    override fun onError(e: Throwable) {
                        nonNullView.showErrorMessage(
                            e.message ?: "an error happened in streaming the data"
                        )
                    }

                    override fun onSuccess(t: List<Product>) {
                        cachedProducts = t.toMutableList()
                        nonNullView.hideLoading()
                        nonNullView.showProducts(t)

                    }

                })
        }
    }

    override fun onAttach(view: Contract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun onDestroy() {
        disposableList.clear()
    }

    override fun updateData(product: Product, position: Int) {
        cachedProducts?.apply {
            this[position] = product.copy(isFavorite = !product.isFavorite)
        }
        cachedProducts?.let { view?.notifyDataUpdate(it) }
    }
}