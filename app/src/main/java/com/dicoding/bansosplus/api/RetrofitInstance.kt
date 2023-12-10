package com.dicoding.bansosplus.api

import com.dicoding.bansosplus.Constants
import com.dicoding.bansosplus.interfaces.AuthApi
import com.dicoding.bansosplus.interfaces.BansosApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val bansosApi: BansosApi by lazy {
        retrofit.create(BansosApi::class.java)
    }
}