package com.dicoding.bansosplus.api

import com.dicoding.bansosplus.interfaces.BansosApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://35.202.238.22:8001")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BansosApi by lazy {
        retrofit.create(BansosApi::class.java)
    }
}