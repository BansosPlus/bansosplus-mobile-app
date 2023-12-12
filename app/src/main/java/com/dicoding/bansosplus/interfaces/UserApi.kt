package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface UserApi {
    @GET("api/users")
    suspend fun get(@Header("Authorization") token: String): Response<UserResponse>

    @Multipart
    @PUT("api/users")
    suspend fun update(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Response<UserResponse>
}