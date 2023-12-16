package com.dicoding.bansosplus.navigation.views.bansos.acceptedBansos

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import java.text.SimpleDateFormat
import java.util.Locale

class DiterimaBansosActivity : AppCompatActivity(){

    private lateinit var activitySessionManager: SessionManager
    private lateinit var viewModel: DiterimaBansosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diterima_bansos)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val intent = intent
        if (intent.hasExtra("bansosRegistrationId")) {
            val bansosRegistrationId = intent.getStringExtra("bansosRegistrationId")

            viewModel = ViewModelProvider(this, DiterimaBansosViewModelFactory(activitySessionManager)).get(
                DiterimaBansosViewModel::class.java)

            if (bansosRegistrationId != null) {
                viewModel.get(bansosRegistrationId)
            }

            viewModel.acceptedBansosItem.observe(this, Observer { bansosDetails ->

                if (bansosDetails != null) {

                    val bansosImageView: ImageView = findViewById(R.id.bansosImageView)
                    val bansosTitleView: TextView = findViewById(R.id.textViewBansosTitle)
                    val bansosExpiryDateView: TextView = findViewById(R.id.textViewBansosExpiryDate)
                    val bansosDescriptionView: TextView = findViewById(R.id.textViewBansosDescription)

                    bansosTitleView.text = bansosDetails.name
                    bansosDetails.updatedAt?.let { value ->
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
