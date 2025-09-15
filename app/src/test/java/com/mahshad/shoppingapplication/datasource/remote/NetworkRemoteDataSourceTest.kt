package com.mahshad.shoppingapplication.datasource.remote

import com.mahshad.shoppingapplication.data.datasource.remote.NetworkRemoteDataSource
import com.mahshad.shoppingapplication.data.models.response.ProductDTO
import com.mahshad.shoppingapplication.data.network.ApiService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response


class NetworkRemoteDataSourceTest {
    @Mock
    private lateinit var fakeApiService: ApiService
    private lateinit var networkRemoteDataSource: NetworkRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        networkRemoteDataSource = NetworkRemoteDataSource(fakeApiService)
    }

    @Test
    fun `getProducts - when repository returns data - product list is returned`() {
        val testObserver = TestObserver<Response<List<ProductDTO>>>()
        val expectedResponse: Response<List<ProductDTO>> = Response
            .success(listOf(ProductDTO.DEFAULT))
        whenever(fakeApiService.getProducts())
            .thenReturn(
                Single.just(expectedResponse)
            )
        networkRemoteDataSource.getProducts().subscribe(testObserver)
        testObserver.assertValue(expectedResponse)
    }

    @Test
    fun `getCategories - when repository returns data - category list is returned`() {
        val testObserver = TestObserver<Response<List<String>>>()
        val expectedResponse: Response<List<String>> = Response
            .success(listOf(ProductDTO.DEFAULT.category!!))
        whenever(fakeApiService.getCategories())
            .thenReturn(Single.just(expectedResponse))
        networkRemoteDataSource.getCategories().subscribe(testObserver)
        testObserver.assertValue(expectedResponse)
    }
}