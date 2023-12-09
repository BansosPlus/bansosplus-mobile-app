package com.dicoding.bansosplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.api.RetrofitInstance
import com.dicoding.bansosplus.databinding.ActivityLoginBinding
import com.dicoding.bansosplus.interfaces.AuthApi
import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.models.auth.LoginResponse
import com.dicoding.bansosplus.navigation.BottomNavActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.apply {
            btnMasuk.setOnClickListener{
                login(
                    etEmail.text.toString().trim(),
                    etPassword.text.toString().trim()
                )
//                val intent = Intent(this@LoginActivity, BottomNavActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    private fun login(email: String, password: String) {
        val request = LoginRequest()
        request.email = email
        request.password = password

        RetrofitInstance.authApi.login(request).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()

                if (response.code() == 200) {
                    val user = loginResponse?.data
                    user?.name?.let { sessionManager.saveName(it) }
                    user?.token?.let { sessionManager.saveToken(it) }

                    val intent = Intent(this@LoginActivity, BottomNavActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.message?.let { Log.e("Error", it) }
            }
        })
    }
}