package com.mahshad.shoppingapplication.ui.mainActivity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mahshad.shoppingapplication.R
import com.mahshad.shoppingapplication.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityBinding: MainActivityBinding
    lateinit var bottomNavigator: BottomNavigationView
    lateinit var fragmentContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        bottomNavigator = mainActivityBinding.bottomNavigationView
        fragmentContainer = mainActivityBinding.fragmentContainer

        setContentView(mainActivityBinding.root)

        bottomNavigator.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.products -> fragmentReplacement(ProductFragment())
                R.id.categories -> fragmentReplacement(CategoriesFragment())
                else -> false
            }
        }


    }

    private fun fragmentReplacement(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
        return true
    }
}
