package com.mahshad.shoppingapplication.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.mahshad.shoppingapplication.data.datasource.local.user.SharedPrefUserLocalDataSource
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SharedPrefUserLocalDataSourceTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor

    private lateinit var testScheduler: TestScheduler
    private lateinit var sharedPrefUserLocalDataSource: SharedPrefUserLocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testScheduler = TestScheduler()

        // Tell the mock context to return the mock SharedPreferences object
        whenever(context.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences)

        sharedPrefUserLocalDataSource = SharedPrefUserLocalDataSource(context, testScheduler)

        // Mock the behavior of the SharedPreferences and Editor objects
        whenever(mockSharedPreferences.edit()).thenReturn(mockEditor)
        whenever(mockEditor.putString(anyString(), anyString())).thenReturn(mockEditor)

        // Mock the apply() behavior to make it synchronous for the test
        whenever(mockEditor.apply()).then { }
    }

    @Test
    fun `saveUser_completes successfully and calls putString`() {
        // GIVEN
        val key = "test_key"
        val value = "test_value"

        // WHEN
        val completable = sharedPrefUserLocalDataSource.saveUser(key, value).test()

        // Trigger the actions scheduled on the TestScheduler
        testScheduler.triggerActions()

        // THEN
        completable.assertComplete()
        verify(mockEditor).putString(key, value)
        verify(mockEditor).apply()
    }
}