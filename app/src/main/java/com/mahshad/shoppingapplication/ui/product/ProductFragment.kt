package com.mahshad.shoppingapplication.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mahshad.shoppingapplication.MyApplication
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product
import javax.inject.Inject


class ProductFragment : Fragment(), ProductContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdaptor
    private lateinit var view: View

    @Inject
    lateinit var productPresenter: ProductPresenter

    companion object {
        private const val TAG = "SubscriptionTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productPresenter.attachView(this)
        view = inflater.inflate(R.layout.fragment_product, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productPresenter.getModifiedProducts()
//        recyclerView = view.findViewById(R.id.products_recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDetach() {
        super.onDetach()
        productPresenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        productPresenter.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        productPresenter.detachView()
        productPresenter.destroy()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showLoading() {
        val progressBar = view.findViewById<ProgressBar>(R.id.loading_progress_bar)
        progressBar.isVisible = true
    }

    override fun hideLoading() {
        val progressBar = view.findViewById<ProgressBar>(R.id.loading_progress_bar)
        progressBar.isVisible = false
    }

    override fun navigateToProductDetail() {
        TODO("Not yet implemented")
    }

    //where I have to set up the adaptor and attach it to the recycler view
    override fun showProducts(products: List<Product>) {
        //TODO
    }

    override fun showModifiedProducts(products: List<Product>) {
//        adapter = ProductAdaptor(products)
//        recyclerView.adapter = adapter
        view.findViewById<TextView>(R.id.text_view).text = products.toString()
    }

    override fun showBookmarkSuccessMessage() {
        Snackbar.make(view, "Successful", Snackbar.LENGTH_LONG)
            .show()
    }
}


