package com.alpharays.composeclean.data.remote

import com.alpharays.composeclean.data.remote.dto.StudentListResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("/userlist")
    suspend fun getUserList(): Response<StudentListResponseDto>
}