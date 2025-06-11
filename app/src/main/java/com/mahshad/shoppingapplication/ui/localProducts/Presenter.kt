package com.mahshad.shoppingapplication.ui.localProducts

import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.repository.product.ProductRepository
import com.mahshad.shoppingapplication.di.ComputationScheduler
import com.mahshad.shoppingapplication.di.MainScheduler
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class Presenter @Inject constructor(
    private val productRepository: ProductRepository,
    @ComputationScheduler private val computationScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : Contract.Presenter {
    private var view: Contract.View? = null
    private var disposableList: MutableList<Disposable> = mutableListOf()

    override fun getProducts() {
        view?.let { nonNullView ->
            val disposable = productRepository.getProducts()
                .subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
                .subscribe({ response: List<Product> ->
                    nonNullView.showProducts(response)
                })
            disposableList.add(disposable)
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
}