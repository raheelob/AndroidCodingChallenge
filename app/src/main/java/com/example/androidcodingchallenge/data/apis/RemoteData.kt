package com.example.androidcodingchallenge.data.apis

import com.example.androidcodingchallenge.data.responses.ErrorData

sealed class RemoteData<out R> {
    data class Success<out T>(val value: T) : RemoteData<T>()
    data class ErrorGeneral(val code: Int? = null, val error: ErrorData? = null) : RemoteData<Nothing>()
    object RemoteErrorByNetwork : RemoteData<Nothing>()
    object Loading : RemoteData<Nothing>()
    object Error401: RemoteData<Nothing>()
}
