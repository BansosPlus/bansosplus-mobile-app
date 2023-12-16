package com.dicoding.bansosplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.dicoding.bansosplus.databinding.ActivityWelcomeBinding
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.navigation.views.home.HomeFragment
import com.dicoding.bansosplus.ui.login.LoginActivity
import com.dicoding.bansosplus.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding :ActivityWelcomeBinding
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.apply {
            btnMasuk.setOnClickListener{
                val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnDaftar.setOnClickListener{
                val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}