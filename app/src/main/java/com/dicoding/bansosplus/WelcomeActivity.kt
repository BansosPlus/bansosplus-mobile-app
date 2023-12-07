package com.dicoding.bansosplus

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.bansosplus.databinding.ActivityWelcomeBinding
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.ui.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding :ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnMasuk.setOnClickListener{
                val intent = Intent(this@WelcomeActivity, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnDaftar.setOnClickListener{
                val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}