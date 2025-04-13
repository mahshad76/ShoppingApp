package com.mahshad.shoppingapplication.ui.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mahshad.shoppingapplication.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Find the BottomNavigationView (optional, but good practice)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
}}
