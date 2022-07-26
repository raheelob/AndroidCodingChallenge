package com.example.androidcodingchallenge.data.repository

import com.example.androidcodingchallenge.data.apis.RemoteData
import com.example.androidcodingchallenge.data.responses.GetProductListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface ListItemsRepository {
     fun getItemsList(): Flow<RemoteData<GetProductListResponse>>
}