package com.mahshad.shoppingapplication.ui.localProducts

import com.mahshad.shoppingapplication.data.models.Product
import io.reactivex.Single

class Presenter: Contract.Presenter {
    override fun getProducts(): Single<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun onAttach() {
        TODO("Not yet implemented")
    }

    override fun onDetach() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}