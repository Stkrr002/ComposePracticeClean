package com.alpharays.composeclean.data

import com.alpharays.composeclean.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    UserRepository {
    override suspend fun getUserList(): List<String> {
        return apiServices.getUserList()
    }
}