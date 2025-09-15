package com.mahshad.shoppingapplication.ui

import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.repository.product.DefaultProductRepository
import com.mahshad.shoppingapplication.ui.product.ProductFragment
import com.mahshad.shoppingapplication.ui.product.ProductPresenter
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class ProductPresenterTest {

    @Mock
    private lateinit var fakeProductRepository: DefaultProductRepository

    @Mock
    private lateinit var fakeProductFragment: ProductFragment

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var productPresenter: ProductPresenter
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testScheduler = TestScheduler()
        productPresenter = ProductPresenter(
            fakeProductRepository,
            compositeDisposable,
            ioScheduler = testScheduler,
            mainScheduler = testScheduler
        )
        productPresenter.attachView(fakeProductFragment)
    }

    @Test
    fun `getModifiedProducts - when the repository returns data - callsCorrectViewMethods`() {
        val mockedProducts = listOf(Product.DEFAULT)

        whenever(fakeProductRepository.getModifiedProducts())
            .thenReturn(Flowable.just(mockedProducts))

        // When
        productPresenter.getModifiedProducts()

        // Without testScheduler.triggerActions(),
        // the Flowable's emission and subsequent processing (including the onNext lambda)
        // are not executed, so hideLoading() is never called.
        testScheduler.triggerActions()

        val inOrder = inOrder(fakeProductFragment)

        // Verify that showLoading() is called first
        inOrder.verify(fakeProductFragment).showLoading()

        // Verify that hideLoading() is called after the stream completes
        inOrder.verify(fakeProductFragment).hideLoading()

        // Verify that showModifiedProducts() is called with the correct data
        inOrder.verify(fakeProductFragment).showModifiedProducts(mockedProducts)

        // Ensure no other methods were called on the view
        verifyNoMoreInteractions(fakeProductFragment)
    }
}