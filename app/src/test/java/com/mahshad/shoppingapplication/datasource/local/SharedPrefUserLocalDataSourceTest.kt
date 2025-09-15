package com.mahshad.shoppingapplication.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.mahshad.shoppingapplication.data.datasource.local.user.SharedPrefUserLocalDataSource
import com.mahshad.shoppingapplication.data.datasource.local.user.USER_INFO
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.concurrent.TimeUnit

class SharedPrefUserLocalDataSourceTest {
    @Mock
    private lateinit var fakeSharedPreferences: SharedPreferences

    @Mock
    private lateinit var fakeContext: Context

    @Mock
    private lateinit var fakeSharedPreferenceEditor: SharedPreferences.Editor
    private lateinit var sharedPreferenceLocalDataSource: SharedPrefUserLocalDataSource
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sharedPreferenceLocalDataSource = SharedPrefUserLocalDataSource(
            fakeContext,
            testScheduler
        )
        whenever(
            fakeContext
                .getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)
        )
            .thenReturn(fakeSharedPreferences)
        whenever(fakeSharedPreferences.edit())
            .thenReturn(fakeSharedPreferenceEditor)
        whenever(
            fakeSharedPreferenceEditor
                .putString(any(), any())
        )
            .thenReturn(fakeSharedPreferenceEditor)
        whenever(fakeSharedPreferenceEditor.remove(any()))
            .thenReturn(fakeSharedPreferenceEditor)
    }

    @Test
    fun `saveUser_completes successfully`() {
        val testObserver = sharedPreferenceLocalDataSource
            .saveUser("userKey", "userData")
            .test()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        testObserver.assertComplete()
        testObserver.assertNoErrors()

        verify(fakeSharedPreferences).edit()
        verify(fakeSharedPreferenceEditor).putString("userKey", "userData")
        verify(fakeSharedPreferenceEditor).apply()
    }
}
