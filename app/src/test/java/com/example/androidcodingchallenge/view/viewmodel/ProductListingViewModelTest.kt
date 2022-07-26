package com.example.androidcodingchallenge.view.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidcodingchallenge.CoroutineTestRule
import com.example.androidcodingchallenge.data.apis.APIsCollection
import com.example.androidcodingchallenge.data.repository.ListItemsRepository
import com.example.androidcodingchallenge.data.repository.ListItemsRepositoryImpl
import com.example.androidcodingchallenge.data.responses.models.Price
import com.example.androidcodingchallenge.data.responses.models.ProductsItem
import com.example.androidcodingchallenge.view.usecases.ProductItemsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.modules.junit4.PowerMockRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(PowerMockRunner::class)
class ProductListingViewModelTest {

    private inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
    private val apiService = mock<APIsCollection>()
    private val listItemsRepository: ListItemsRepository = ListItemsRepositoryImpl(apiService)
    private val useCase: ProductItemsUseCase = ProductItemsUseCase(listItemsRepository)

    private lateinit var productListingViewModel: ProductListingViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @Before
    fun before() {
        productListingViewModel = ProductListingViewModel(useCase)
    }

    @Test
    fun `test populate List`() {
        testScope.launch {
            val list: List<ProductsItem> = object : ArrayList<ProductsItem>() {
                init {
                    add(
                        ProductsItem(
                            longDescription = "Lorem ipsum dolor sit amet",
                            color = "Blue",
                            releaseDate = 1459629605,
                            price = Price(
                                value = 281.51,
                                currency = "EUR"
                            ),
                            imageURL = "https://kredit.check24.de/konto-kredit/ratenkredit/nativeapps/imgs/01.png",
                            name = "Blue Circle",
                            available = false,
                            rating = 1.0,
                            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr",
                            colorCode = "BBDEFB",
                            id = 2,
                            type = "Circle",
                            saved = false
                        )
                    )
                }
            }
            productListingViewModel.populateList(list)
            productListingViewModel.tasksEvent.collect {
                when (it) {
                    is ProductListingViewModel.ShapesEvent.Data -> {
                        assertTrue(it.data.isNotEmpty())
                    }
                    else -> {
                        assertFalse(true)
                    }
                }
            }
        }
    }

    @After
    @Throws(Exception::class)
    fun tearDownClass() {
        Mockito.framework().clearInlineMocks()
    }
}