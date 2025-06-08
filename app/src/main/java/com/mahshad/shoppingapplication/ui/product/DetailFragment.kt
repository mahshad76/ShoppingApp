package com.mahshad.shoppingapplication.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mahshad.shoppingapplication.R

class DetailFragment : Fragment() {

    private lateinit var toolBar: Toolbar
    private lateinit var textView: TextView

    companion object {
        const val ARG_MESSAGE = "param_message"
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
        (activity as? AppCompatActivity)?.setSupportActionBar(toolBar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.title = "List of products"
        toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
        arguments?.let {
            val message = it.getString(ARG_MESSAGE)
            textView.text = message
        }
        return view
    }
}
