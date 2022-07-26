package com.example.androidcodingchallenge.view.usecases

import com.example.androidcodingchallenge.data.apis.NetworkExecutor
import com.example.androidcodingchallenge.data.apis.RemoteData
import com.example.androidcodingchallenge.data.repository.ListItemsRepository
import com.example.androidcodingchallenge.data.responses.GetProductListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductItemsUseCase @Inject constructor(private val listItemsRepository: ListItemsRepository) :
    NetworkExecutor<ProductItemsUseCase.Params, GetProductListResponse>() {

    override fun runUseCase(parameter: Params?): Flow<RemoteData<GetProductListResponse>> {
        return listItemsRepository.getItemsList()
    }

    class Params
}