package com.mahshad.shoppingapplication.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.common.MainActivityCallbacks

class DetailFragment : Fragment() {

    private lateinit var toolBar: Toolbar
    private lateinit var textView: TextView
    private lateinit var mainActivityCallback: MainActivityCallbacks
    private lateinit var appBar: AppBarLayout

    companion object {
        const val ARG_MESSAGE = "param_message"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityCallbacks) {
            mainActivityCallback = context
        } else {
            throw RuntimeException("$context must implement MainActivityCallback")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        textView = view.findViewById(R.id.description_text)
        toolBar = view.findViewById(R.id.toolbar)
        appBar = activity?.findViewById(R.id.appBar)!!
        appBar.visibility = GONE
        (activity as? AppCompatActivity)?.setSupportActionBar(toolBar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.title = "Details"
        toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
            mainActivityCallback.showBottomNavigationView()
            appBar.visibility = VISIBLE
        }
        arguments?.let {
            val message = it.getString(ARG_MESSAGE)
            textView.text = message
        }
        mainActivityCallback.hideBottomNavigationView()
        return view
    }
}
