package com.mahshad.shoppingapplication.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahshad.shoppingapplication.MyApplication
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.repository.product.ProductRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ProductFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdaptor
    private var dataList = mutableListOf<Product>()
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var productRepository: ProductRepository

    @Inject
    lateinit var productPresenter: ProductPresenter

    companion object {
        private const val TAG = "SubscriptionTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO() getting the app component
        (context?.applicationContext as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_product, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //subscribeToDataFlowable()
        dataList = mutableListOf(
            Product(
                "a",
                "a",
                1,
                "a",
                2.0,
                null,
                "a",
                false
            )
        )
        recyclerView = view.findViewById(R.id.products_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdaptor(dataList)
        recyclerView.adapter = adapter
    }
}


