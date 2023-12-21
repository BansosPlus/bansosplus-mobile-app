package com.dicoding.bansosplus.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.WelcomeActivity
import com.dicoding.bansosplus.databinding.ActivityRegisterBinding
import com.dicoding.bansosplus.models.auth.RegisterRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.repository.AuthRepository
import com.dicoding.bansosplus.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            backButton.setOnClickListener {
                val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }

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

                MaterialAlertDialogBuilder(this@RegisterActivity)
                    .setTitle(getString(R.string.register_success))
                    .setMessage("Silahkan login kembali.")
                    .setPositiveButton("Masuk"){
                            dialog, _ -> dialog.dismiss()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }.create().show()

            } else {
                // TODO: Alert
                Toast.makeText(
                    applicationContext,
                    getString(R.string.register_failed_response),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            // TODO: Alert
            Toast.makeText(
                applicationContext,
                getString(R.string.register_failed_pass_not_match),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}