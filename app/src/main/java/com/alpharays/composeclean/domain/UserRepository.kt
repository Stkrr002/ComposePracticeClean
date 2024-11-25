package com.alpharays.composeclean.domain

import com.alpharays.composeclean.domain.model.StudentListResponse
import com.alpharays.composeclean.utils.APIResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserList(): Flow<APIResponse<StudentListResponse?>>

}