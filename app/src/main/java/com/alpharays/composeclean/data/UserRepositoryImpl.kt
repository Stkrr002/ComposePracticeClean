package com.alpharays.composeclean.data

import com.alpharays.composeclean.domain.UserRepository
import com.alpharays.composeclean.utils.APIResponse
import com.alpharays.composeclean.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    UserRepository {

    @Inject
    lateinit var responseHandler: ResponseHandler
    override suspend fun getUserList(): Flow<APIResponse<List<String>>> =

        responseHandler.callAPI {
            apiServices.getUserList("")
        }
}