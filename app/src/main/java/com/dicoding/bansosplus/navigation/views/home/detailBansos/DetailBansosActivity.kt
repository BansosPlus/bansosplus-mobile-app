package com.dicoding.bansosplus.navigation.views.home.detailBansos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import java.text.SimpleDateFormat
import java.util.Locale
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.SessionManager

class DetailBansosActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var viewModel: DetailBansosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bansos)

        sessionManager = SessionManager(this)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        val intent = intent

        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")
            viewModel = ViewModelProvider(this, DetailBansosViewModelFactory(sessionManager)).get(DetailBansosViewModel::class.java)

            if (bansosId != null) {
                viewModel.get(bansosId)
            }

            viewModel.bansosItem.observe(this, Observer { bansosDetails ->
                if (bansosDetails != null) {

                    val bansosImageView: ImageView = findViewById(R.id.bansosImageView)
                    val bansosIdTextView: TextView = findViewById(R.id.textViewBansosId)
                    val bansosTitleView: TextView = findViewById(R.id.textViewBansosTitle)
                    val bansosExpiryDateView: TextView = findViewById(R.id.textViewBansosExpiryDate)
                    val bansosDescriptionView: TextView = findViewById(R.id.textViewBansosDescription)

                    bansosIdTextView.text = bansosId
                    bansosTitleView.text = bansosDetails.name
                    bansosDetails.expiryDate?.let { value ->
                        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(value)
                        bansosExpiryDateView?.text = formattedDate
                    }
                    bansosDescriptionView.text = bansosDetails.description
                    Glide.with(this)
                        .load(bansosDetails.imageUrl)
                        .into(bansosImageView)
                } else {
                    Toast.makeText(this, "Failed to get bansos details", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        } else {
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}