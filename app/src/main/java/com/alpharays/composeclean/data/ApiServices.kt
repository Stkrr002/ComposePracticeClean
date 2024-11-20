package com.alpharays.composeclean.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServices {
    @GET
    suspend fun getUserList(@Url listUrl: String): Response<List<String>>
}