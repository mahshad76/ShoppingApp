package com.mahshad.shoppingapplication.ui.localProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product

class LocalProductFragment : Fragment(), Contract.View {

    private lateinit var view: View
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_local_product, container, false)
        progressBar = view.findViewById(R.id.loading_progress_bar2)
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
        //set the adaptor and the recycler view
    }
}