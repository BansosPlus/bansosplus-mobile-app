package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.BansosResponse
import retrofit2.Response
class RetrofitRepository {
    suspend fun getBansos(): Response<BansosResponse> {
        return RetrofitInstance.api.getBansos()
    }
}