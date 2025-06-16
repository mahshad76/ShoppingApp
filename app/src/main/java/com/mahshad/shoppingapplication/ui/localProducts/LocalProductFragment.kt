package com.mahshad.shoppingapplication.ui.localProducts

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

class LocalProductFragment : Fragment(), Contract.View, ClickListener {

    private lateinit var view: View
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as MyApplication).appComponent.inject(this)
        presenter.onAttach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_local_product, container, false)
        progressBar = view.findViewById(R.id.loading_progress_bar2)
        recyclerView = view.findViewById(R.id.products_recycler_view3)
        adapter = ProductAdapter(this)
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
        presenter.getProducts()
        return view
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

    override fun showProducts(response: List<Product>) {
        adapter.submitList(response.toMutableList())
    }

    override fun clickListener(productId: Int) {
        presenter.updateData(productId)
    }
}