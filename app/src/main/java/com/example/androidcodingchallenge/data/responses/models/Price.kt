package com.example.androidcodingchallenge.data.responses.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("value")
	val value: Double? = null
) : Parcelable