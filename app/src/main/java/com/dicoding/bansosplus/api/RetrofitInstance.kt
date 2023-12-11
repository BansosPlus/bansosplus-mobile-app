package com.dicoding.bansosplus.api

import com.dicoding.bansosplus.Constants
import com.dicoding.bansosplus.interfaces.AuthApi
import com.dicoding.bansosplus.interfaces.BansosApi
import com.dicoding.bansosplus.interfaces.BansosRegistrationApi
import com.dicoding.bansosplus.interfaces.FeedbackApi
import com.dicoding.bansosplus.interfaces.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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

    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    val bansosApi: BansosApi by lazy {
        retrofit.create(BansosApi::class.java)
    }

    val feedbackApi: FeedbackApi by lazy {
        retrofit.create(FeedbackApi::class.java)
    }

    val bansosRegistrationApi: BansosRegistrationApi by lazy {
        retrofit.create(BansosRegistrationApi::class.java)
    }
}