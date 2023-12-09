package com.dicoding.bansosplus.repository

import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.models.auth.BansosResponse
import retrofit2.Response

class BansosRepository {
    suspend fun get(): Response<BansosResponse> {
        return RetrofitInstance.bansosApi.get("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InRlc0BnbWFpbC5jb20iLCJleHAiOjE3MDIxOTY4NTcsImlkIjoxLCJuYW1lIjoiVGVzIiwicm9sZSI6InVzZXIifQ.2gxi9D-XmvW_87rfIzLdxnQMyGmWDtjinI5Biy6ExlY")
    }
}