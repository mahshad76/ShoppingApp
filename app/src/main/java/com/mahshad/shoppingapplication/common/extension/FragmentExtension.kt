package com.mahshad.shoppingapplication.common.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mahshad.shoppingapplication.R

fun AppCompatActivity.replaceFragment(
    @IdRes containerId: Int = R.id.fragment_container, fragment: Fragment
): Boolean {
    supportFragmentManager.beginTransaction().replace(containerId, fragment)
        .commit()
    return true
}