package com.dicoding.bansosplus.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityRegisterBinding
import com.dicoding.bansosplus.models.auth.RegisterRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.repository.AuthRepository
import com.dicoding.bansosplus.ui.login.LoginActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sessionManager: SessionManager
    private val authRepository: AuthRepository = AuthRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.apply {
            btnDaftar.setOnClickListener{
                lifecycleScope.launch() {
                    register(
                        etNama.text.toString().trim(),
                        etEmail.text.toString().trim(),
                        etPhone.text.toString().trim(),
                        etPass1.text.toString().trim(),
                        etPass2.text.toString().trim()
                    )
                }
            }

            tvLoginLink.setOnClickListener{
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private suspend fun register(
        nama: String,
        email: String,
        phone: String,
        pass1: String,
        pass2: String
    ) {
        if (pass1 == pass2) {
            val request = RegisterRequest()
            request.name = nama
            request.email = email
            request.phone = phone
            request.password = pass1

            val response = authRepository.register(request)
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

                val intent = Intent(this@RegisterActivity, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // TODO: Alert
            }
        } else {
            // TODO: Alert
        }
    }
}