package com.mahshad.shoppingapplication.ui.mainActivity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mahshad.shoppingapplication.MyApplication
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.data.network.ApiService
import com.mahshad.shoppingapplication.databinding.MainActivityBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityBinding: MainActivityBinding
    lateinit var bottomNavigator: BottomNavigationView
    lateinit var fragmentContainer: FrameLayout

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)
        mainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        bottomNavigator = mainActivityBinding.bottomNavigationView
        fragmentContainer = mainActivityBinding.fragmentContainer

        setContentView(mainActivityBinding.root)

        bottomNavigator.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.products -> productFragmentReplacement(ProductFragment())
                R.id.categories -> categoryFragmentReplacement(CategoriesFragment())
                else -> false
            }
        }


    }

    private fun productFragmentReplacement(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }

    private fun categoryFragmentReplacement(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}
