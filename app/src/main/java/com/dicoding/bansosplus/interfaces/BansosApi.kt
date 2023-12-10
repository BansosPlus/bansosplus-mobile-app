package com.dicoding.bansosplus.interfaces

import com.dicoding.bansosplus.models.auth.BansosResponse
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BansosApi {
    @GET("api/bansos")
    suspend fun get(@Header("Authorization") token: String): Response<BansosResponse>

    @GET("api/bansos/{id}")
    suspend fun getDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<BansosItem>
}