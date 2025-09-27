package com.mahshad.shoppingapplication.ui

import com.mahshad.shoppingapplication.data.models.Product
import com.mahshad.shoppingapplication.data.repository.product.ProductRepository
import com.mahshad.shoppingapplication.ui.product.ProductContract
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
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var view: ProductContract.View

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var productPresenter: ProductPresenter
    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        testScheduler = TestScheduler()
        productPresenter = ProductPresenter(
            productRepository,
            compositeDisposable,
            ioScheduler = testScheduler,
            mainScheduler = testScheduler
        )
        productPresenter.attachView(view)
    }

    @Test
    fun `getModifiedProducts - when the repository returns data - callsCorrectViewMethods`() {
        val mockedProducts = listOf(Product.DEFAULT)
        whenever(productRepository.getModifiedProducts())
            .thenReturn(Flowable.just(mockedProducts))

        // When
        productPresenter.getModifiedProducts()

        // Without testScheduler.triggerActions(),
        // the Flowable's emission and subsequent processing (including the onNext lambda)
        // are not executed, so hideLoading() is never called.
        testScheduler.triggerActions()

        val inOrder = inOrder(productRepository)

        // Verify that showLoading() is called first
        inOrder.verify(view).showLoading()

        // Verify that hideLoading() is called after the stream completes
        inOrder.verify(view).hideLoading()

        // Verify that showModifiedProducts() is called with the correct data
        inOrder.verify(view).showModifiedProducts(mockedProducts)

        // Ensure no other methods were called on the view
        verifyNoMoreInteractions(view)
    }
}