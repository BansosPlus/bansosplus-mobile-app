package com.dicoding.bansosplus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.databinding.ActivityLoginBinding
import com.dicoding.bansosplus.databinding.ActivityRegisterBinding
import com.dicoding.bansosplus.navigation.BottomNavActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnMasuk.setOnClickListener{
                val intent = Intent(this@LoginActivity, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}