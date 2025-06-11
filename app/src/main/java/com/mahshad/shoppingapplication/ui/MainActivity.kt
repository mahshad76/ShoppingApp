package com.mahshad.shoppingapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mahshad.shoppingapplication.MyApplication
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.common.MainActivityCallbacks
import com.mahshad.shoppingapplication.common.extension.replaceFragment
import com.mahshad.shoppingapplication.databinding.MainActivityBinding
import com.mahshad.shoppingapplication.ui.category.CategoriesFragment
import com.mahshad.shoppingapplication.ui.localProducts.LocalProductFragment
import com.mahshad.shoppingapplication.ui.product.ProductFragment

class MainActivity : AppCompatActivity(), MainActivityCallbacks {

    private lateinit var binding: MainActivityBinding
    private lateinit var bottomNavigator: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)
        binding = MainActivityBinding.inflate(layoutInflater)
        bottomNavigator = binding.bottomNavigationView

        setContentView(binding.root)
        replaceFragment(fragment = ProductFragment())
        setupUiListener()
    }

    private fun setupUiListener() {
        bottomNavigator.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.products -> replaceFragment(fragment = ProductFragment())

                R.id.categories -> replaceFragment(fragment = CategoriesFragment())

                R.id.localProducts -> replaceFragment(fragment = LocalProductFragment())

                else -> false
            }
        }
    }

    override fun hideBottomNavigationView() {
        bottomNavigator.isVisible = false
    }

    override fun showBottomNavigationView() {
        bottomNavigator.isVisible = true
    }
}
