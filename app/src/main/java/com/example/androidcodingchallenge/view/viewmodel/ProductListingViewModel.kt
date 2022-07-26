package com.example.androidcodingchallenge.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcodingchallenge.data.apis.RemoteData
import com.example.androidcodingchallenge.data.responses.models.ProductsItem
import com.example.androidcodingchallenge.view.usecases.ProductItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val useCase: ProductItemsUseCase
) : ViewModel() {

    private val tasksEventChannel = Channel<ShapesEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()
    lateinit var unFilteredList: List<ProductsItem>

    fun btnAvailable() =
        viewModelScope.launch {
            if (unFilteredList.isEmpty()) {
                tasksEventChannel.send(ShapesEvent.Error)
            } else
                tasksEventChannel.send(ShapesEvent.Available(unFilteredList.filter { it.available == true }))
        }

    fun btnNotAvailable() =
        viewModelScope.launch {
            if (unFilteredList.isEmpty()) {
                tasksEventChannel.send(ShapesEvent.Error)
            } else
                tasksEventChannel.send(ShapesEvent.NotAvailable(unFilteredList.filter { it.available == false }))
        }

    fun btnAll() =
        viewModelScope.launch {
            if (unFilteredList.isEmpty()) {
                tasksEventChannel.send(ShapesEvent.Error)
            } else
                tasksEventChannel.send(ShapesEvent.All(unFilteredList))
        }

    fun getProductsList() = viewModelScope.launch {
        tasksEventChannel.send(ShapesEvent.Loading)
        useCase.execute(ProductItemsUseCase.Params()).collect { res ->
            when (res) {
                RemoteData.Loading -> {
                    sendLoading()
                }

                is RemoteData.Success -> {
                    res.value.let {
                        it.products?.let { productList ->
                            populateList(productList)
                        }
                        unFilteredList = it.products!!
                    }
                }

                is RemoteData.RemoteErrorByNetwork -> {
                    sendError()
                }

                is RemoteData.Error401 -> {
                    sendError401()
                }

                is RemoteData.ErrorGeneral -> {
                    generalError()
                }
            }
        }
    }

    private suspend fun sendLoading() {
        tasksEventChannel.send(ShapesEvent.Loading)
    }

    suspend fun populateList(list: List<ProductsItem>) {
        tasksEventChannel.send(ShapesEvent.Data(list))
    }

    private suspend fun sendError() {
        tasksEventChannel.send(ShapesEvent.Error)
    }

    private suspend fun sendError401() {
        tasksEventChannel.send(ShapesEvent.Error401)
    }

    private suspend fun generalError() {
        tasksEventChannel.send(ShapesEvent.Error)
    }

    sealed class ShapesEvent {
        data class Available(val availableProductsList: List<ProductsItem>) : ShapesEvent()
        data class NotAvailable(val unavailableProductsList: List<ProductsItem>) : ShapesEvent()
        data class All(val unFilteredList: List<ProductsItem>) : ShapesEvent()
        object Error : ShapesEvent()
        object Loading : ShapesEvent()
        data class Data(val data: List<ProductsItem>) : ShapesEvent()
        object Error401 : ShapesEvent()
    }

}