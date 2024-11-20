package com.alpharays.composeclean.domain

interface UserRepository {
    suspend fun getUserList(): List<String>

}