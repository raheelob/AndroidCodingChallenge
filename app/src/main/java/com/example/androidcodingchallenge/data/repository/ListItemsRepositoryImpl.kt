package com.example.androidcodingchallenge.data.repository

import com.example.androidcodingchallenge.data.apis.RemoteData
import com.example.androidcodingchallenge.data.apis.APIsCollection
import com.example.androidcodingchallenge.data.responses.GetProductListResponse
import com.example.androidcodingchallenge.data.responses.models.ProductsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ListItemsRepositoryImpl @Inject constructor(
    private val listItemsApi: APIsCollection
) :
    ListItemsRepository {
    private lateinit var item: ProductsItem
    override fun getItemsList(
    ): Flow<RemoteData<GetProductListResponse>> =
        flow {
            val response = listItemsApi.getListItems()
            emit(RemoteData.Success(response))
        }.flowOn(Dispatchers.IO)

}