package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("api/users")
    suspend fun get(@Header("Authorization") token: String): Response<UserResponse>
}