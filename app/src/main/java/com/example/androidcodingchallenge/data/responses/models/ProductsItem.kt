package com.example.androidcodingchallenge.data.responses.models

import android.os.Parcelable
import com.example.androidcodingchallenge.data.responses.models.Price
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsItem(

    @field:SerializedName("longDescription")
	val longDescription: String? = null,

    @field:SerializedName("color")
	val color: String? = null,

    @field:SerializedName("releaseDate")
	val releaseDate: Long? = null,

    @field:SerializedName("price")
	val price: Price? = null,

    @field:SerializedName("imageURL")
	val imageURL: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("available")
	val available: Boolean? = null,

    @field:SerializedName("rating")
	val rating: Double? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("colorCode")
	val colorCode: String? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("type")
	val type: String? = null,

    var saved : Boolean = false
) : Parcelable