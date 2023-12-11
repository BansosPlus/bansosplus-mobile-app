package com.dicoding.bansosplus.navigation.views.home.detailBansos.pengajuanBansos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.models.auth.BansosRegistrationRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.repository.BansosRegistrationRepository
import com.dicoding.bansosplus.repository.UserRepository
import kotlinx.coroutines.launch

class PengajuanBansosActivity : AppCompatActivity() {
    private lateinit var activitySessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_bansos)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val intent = intent
        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")

            if (bansosId != null) {
                lifecycleScope.launch() {
                    try {
                        val response = UserRepository(activitySessionManager).get()
                        if (response.isSuccessful) {
                            val user = response.body()?.data
                            Log.i("BANSOS", "Get user successfully")

                            if (user != null) {
                                findViewById<EditText>(R.id.et_nik).setText(user.nik)
                                findViewById<EditText>(R.id.et_nama).setText(user.name)
                                findViewById<EditText>(R.id.et_no_kk).setText(user.noKk)
                                findViewById<EditText>(R.id.et_income).setText(user.income)
                            }
                        } else {
                            Log.e("BANSOS", "Response failed")
                        }
                    } catch (e: Exception) {
                        Log.e("BANSOS", "Connection failed")
                    }
                }

                val btnAjukanBantuan: Button = findViewById(R.id.btn_ajukan_bantuan)
                btnAjukanBantuan.setOnClickListener {
                    lifecycleScope.launch() {
                        registerBansos(
                            bansosId.toInt(),
                            findViewById<EditText>(R.id.et_nik).text.toString().trim(),
                            findViewById<EditText>(R.id.et_nama).text.toString().trim(),
                            findViewById<EditText>(R.id.et_no_kk).text.toString().trim()
                        )
                    }
                }
            }
        } else {
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private suspend fun registerBansos(bansos_id: Int, nik: String, nama: String, no_kk: String) {
        val request = BansosRegistrationRequest()
        request.bansosId = bansos_id
        request.nik = nik
        request.name = nama
        request.noKk = no_kk

        try {
            val response = BansosRegistrationRepository(activitySessionManager).registerBansos(request)
            if (response.isSuccessful) {
                Log.i("BANSOS", "Register bansos successfully")

                val intent = Intent(this, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.e("BANSOS", "Response failed")
            }
        } catch (e: Exception) {
            Log.e("BANSOS", "Connection failed")
        }
    }
}