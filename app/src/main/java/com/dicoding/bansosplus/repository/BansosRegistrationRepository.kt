package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.BansosStatusResponse
import retrofit2.Response

class BansosRegistrationRepository(private val sessionManager: SessionManager) {
    suspend fun getBansosRegistrationByUser(): Response<BansosStatusResponse> {
        return RetrofitInstance.bansosRegistrationApi.getBansosRegistrationByUser("Bearer " + sessionManager.fetchToken())
    }
}