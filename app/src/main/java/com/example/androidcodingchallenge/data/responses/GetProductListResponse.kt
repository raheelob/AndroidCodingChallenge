package com.example.androidcodingchallenge.data.responses

import com.example.androidcodingchallenge.data.responses.models.Header
import com.example.androidcodingchallenge.data.responses.models.ProductsItem
import com.google.gson.annotations.SerializedName

data class GetProductListResponse(

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("filters")
	val filters: List<String?>? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem>? = null
)