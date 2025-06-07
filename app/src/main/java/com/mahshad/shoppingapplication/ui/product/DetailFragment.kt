package com.mahshad.shoppingapplication.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mahshad.shoppingapplication.R

class DetailFragment : Fragment() {

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
        val textView = view.findViewById<TextView>(R.id.description_text)
        arguments?.let {
            val message = it.getString(ARG_MESSAGE)
            textView.text = message
        }
        return view
    }
}
