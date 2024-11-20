package com.alpharays.composeclean.utils


import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject


class ResponseHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun <T : Any> callAPI(call: suspend () -> Response<T>): Flow<APIResponse<T>> = flow {
        emit(APIResponse.Loading())
        try {
            val apiResponse = call()
            if (apiResponse.isSuccessful && apiResponse.body() != null) {
                emit(APIResponse.Success(apiResponse.body()!!))
            } else {
                val errorObj = apiResponse.errorBody()!!.charStream().readText()
                emit(
                    APIResponse.Error(
                        message =
                        getErrorMessage(
                            errorBody = errorObj
                        ), errorBody = errorObj
                    )
                )
            }
        } catch (e: IOException) {
            emit(APIResponse.Error(getErrorMessage(e.message)))
        } catch (socketTimeOutException: SocketTimeoutException) {
            emit(APIResponse.Error(getErrorMessage(socketTimeOutException.message)))
        } catch (e: Exception) {
            emit(APIResponse.Error(getErrorMessage(e.message)))
        }
    }

    private fun getErrorMessage(errorBody: String?): String {
        return errorBody ?: "Something went wrong"
    }


}