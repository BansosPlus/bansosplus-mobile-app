package com.dicoding.bansosplus.navigation.views.home.detailBansos.pengajuanBansos

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.BansosItem
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

            lifecycleScope.launch() {
                try {
                    val response = UserRepository(activitySessionManager).get()
                    if (response.isSuccessful) {
                        val user = response.body()?.data
                        Log.i("BANSOS", "Get user successfully")

                        if (user != null) {
                            val nik: EditText = findViewById(R.id.et_nik)
                            val nama: EditText = findViewById(R.id.et_nama)
                            val no_kk: EditText = findViewById(R.id.et_no_kk)
                            val income: EditText = findViewById(R.id.et_income)

                            nik.setText(user.nik)
                            nama.setText(user.name)
                            no_kk.setText(user.no_kk)
                            income.setText(user.income)
                        }
                    } else {
                        Log.e("BANSOS", "Response failed")
                    }
                } catch (e: Exception) {
                    Log.e("BANSOS", "Connection failed")
                }
            }
        } else {
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}