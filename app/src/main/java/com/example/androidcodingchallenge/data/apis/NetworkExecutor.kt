package com.example.androidcodingchallenge.data.apis

import com.example.androidcodingchallenge.data.responses.ErrorData
import com.example.androidcodingchallenge.utils.ErrorConvertor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import okio.IOException
import retrofit2.HttpException

abstract class NetworkExecutor<in P, out R>() {

    abstract fun runUseCase(parameter: P?): Flow<RemoteData<R>>

     fun execute(parameters: P?): Flow<RemoteData<R>> {
        return runUseCase(parameters).buffer().catch { e ->
            when (e) {
                is IOException -> emit(RemoteData.RemoteErrorByNetwork)
                is HttpException -> {
                    val code = e.code()
                    if (e.code() == 401) {
                        emit(RemoteData.Error401)
                    } else {
                        val errorResponse =
                            ErrorConvertor.parseErrorBody(e)
                        emit(RemoteData.ErrorGeneral(code, errorResponse))
                    }
                }
                else -> {
                    emit(RemoteData.ErrorGeneral(-1, ErrorData(code = 1, message = "UnknownError")))
                }
            }
        }
    }
}