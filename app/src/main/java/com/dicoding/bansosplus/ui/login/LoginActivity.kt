package com.dicoding.bansosplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityLoginBinding
import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.repository.AuthRepository
import com.dicoding.bansosplus.ui.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    private val authRepository: AuthRepository = AuthRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.apply {
            btnMasuk.setOnClickListener{
                lifecycleScope.launch() {
                    login(
                        etEmail.text.toString().trim(),
                        etPassword.text.toString().trim()
                    )
                }
            }

            tvRegisLink.setOnClickListener{
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private suspend fun login(email: String, password: String) {
        val request = LoginRequest()
        request.email = email
        request.password = password

        val response = authRepository.login(request)
        if (response.isSuccessful) {
            val user = response.body()?.data
            user?.token?.let {
                Log.d("SaveToken", "Saving Token: $it")
                sessionManager.saveToken(it)
            }

            user?.name?.let {
                Log.d("SaveName", "Saving Name: $it")
                sessionManager.saveName(it)
            }

            val intent = Intent(this@LoginActivity, BottomNavActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // TODO: Alert
        }
    }
}