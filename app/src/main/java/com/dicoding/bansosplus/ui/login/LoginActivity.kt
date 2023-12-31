package com.dicoding.bansosplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.WelcomeActivity
import com.dicoding.bansosplus.databinding.ActivityLoginBinding
import com.dicoding.bansosplus.models.auth.LoginRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.repository.AuthRepository
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
            backButton.setOnClickListener {
                val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnMasuk.setOnClickListener{
                    login(
                        etEmail.text.toString().trim(),
                        etPassword.text.toString().trim()
                    )
            }
        }
    }

    private  fun login(email: String, password: String) {
        val request = LoginRequest()
        request.email = email
        request.password = password

        lifecycleScope.launch {
            val response = authRepository.login(request)
            if (response.isSuccessful) {
                val user = response.body()?.data
                user?.name?.let { sessionManager.saveName(it) }
                user?.token?.let { sessionManager.saveToken(it) }
                user?.role?.let { sessionManager.saveRole(it) }

                Toast.makeText(
                    applicationContext,
                    getString(R.string.login_success),
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@LoginActivity, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.login_failed),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}