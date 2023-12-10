package com.dicoding.bansosplus.navigation.views.home.detailBansos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.dicoding.bansosplus.R

class DetailBansosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bansos)

        val intent = intent
        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")
            val bansosIdTextView: TextView = findViewById(R.id.textViewBansosId)
            bansosIdTextView.text = bansosId
        } else {
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}