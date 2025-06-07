package com.mahshad.shoppingapplication.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mahshad.shoppingapplication.MyApplication
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product
import javax.inject.Inject


class ProductFragment : Fragment(), ProductContract.View, OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
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
        recyclerView = view.findViewById(R.id.products_recycler_view)
        progressBar = view.findViewById(R.id.loading_progress_bar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productPresenter.getModifiedProducts()
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
        progressBar.isVisible = true
    }

    override fun hideLoading() {
        progressBar.isVisible = false
    }

    override fun navigateToProductDetail() {
        TODO("Not yet implemented")
    }

    override fun showProducts(products: List<Product>) {
        //TODO implement it R&D (having the like as the session is up)
    }

    override fun showModifiedProducts(products: List<Product>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProductAdaptor(products, this)
    }

    override fun showBookmarkSuccessMessage() {
        Snackbar.make(view, "Like :)", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showUnBookmarkSuccessMessage() {
        Snackbar.make(view, "Dislike :(", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onFavoriteClick(productItem: Product) {
        productItem.copy(isFavorite = !productItem.isFavorite).let { updatedProduct ->
            if (updatedProduct.isFavorite) {
                productPresenter.bookmarkProduct(updatedProduct)
                showBookmarkSuccessMessage()
            } else {
                updatedProduct.id?.let {
                    productPresenter.unBookmarkProduct(it)
                    showUnBookmarkSuccessMessage()
                }
            }
        }
    }

    override fun onRowClick(productItem: Product) {
        val detailFragment = DetailFragment()
        val bundle = Bundle().apply {
            putString(DetailFragment.ARG_MESSAGE, productItem.description)
        }
        detailFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                detailFragment
            )
            .addToBackStack(null)
            .commit()
    }
}