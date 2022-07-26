package com.example.androidcodingchallenge.data.apis

import com.example.androidcodingchallenge.data.responses.GetProductListResponse
import retrofit2.http.GET

interface APIsCollection {
    @GET("products-test.json")
    suspend fun getListItems(
    ): GetProductListResponse
}