package com.alpharays.composeclean.data

import com.alpharays.composeclean.data.dto.StudentListResponseDto
import com.alpharays.composeclean.data.mapper.toStudentResponse
import com.alpharays.composeclean.domain.UserRepository
import com.alpharays.composeclean.domain.model.StudentListResponse
import com.alpharays.composeclean.utils.APIResponse
import com.alpharays.composeclean.utils.ResponseHandler
import com.alpharays.composeclean.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    UserRepository {

    @Inject
    lateinit var responseHandler: ResponseHandler
    override suspend fun getUserList(): Flow<APIResponse<StudentListResponse?>> {
        val response = responseHandler.callAPI {
            apiServices.getUserList()
        }

        val studentListResponse = response.map {
            it.map { itt ->
                itt.toStudentResponse()
            }
        }

        return studentListResponse
    }
}