package com.mahshad.shoppingapplication.datasource.local

import android.content.Context
import android.content.SharedPreferences
import org.mockito.Mock

class SharedPrefUserLocalDataSourceTest {
    @Mock
    private lateinit var fakeSharedPreferences: SharedPreferences

    @Mock
    private lateinit var fakeContext: Context

    @Mock
    private lateinit var fakeSharedPreferenceEditor: SharedPreferences.Editor
}
