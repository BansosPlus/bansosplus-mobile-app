package com.dicoding.bansosplus

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://35.202.238.22:8001")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}