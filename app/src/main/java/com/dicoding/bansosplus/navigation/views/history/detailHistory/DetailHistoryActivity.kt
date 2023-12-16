package com.dicoding.bansosplus.navigation.views.history.detailHistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityDetailHistoryBinding
import com.dicoding.bansosplus.navigation.data.model.FeedbackItem
import com.dicoding.bansosplus.navigation.views.adapter.FeedbackListAdapter
import com.dicoding.bansosplus.navigation.views.history.detailHistory.feedback.FeedbackActivity
import com.dicoding.bansosplus.navigation.views.home.detailBansos.DetailBansosViewModel
import com.dicoding.bansosplus.navigation.views.home.detailBansos.DetailBansosViewModelFactory
import com.dicoding.bansosplus.navigation.views.home.detailBansos.pengajuanBansos.PengajuanBansosActivity
import java.text.SimpleDateFormat
import java.util.Locale

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding
    private lateinit var activitySessionManager: SessionManager
    private lateinit var viewModel: DetailHistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = binding.backButton
        backButton.setOnClickListener {
            finish()
        }
        val intent = intent
        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")

            viewModel = ViewModelProvider(this, DetailHistoryViewModelFactory(activitySessionManager)).get(
                DetailHistoryViewModel::class.java)

            if (bansosId != null) {
                viewModel.get(bansosId)
            }

            viewModel.bansosItem.observe(this, Observer { bansosDetails ->

                if (bansosDetails != null) {

                    val bansosImageView: ImageView = binding.bansosImageView
                    val bansosTitleView: TextView = binding.textViewBansosTitle
                    val bansosExpiryDateView: TextView = binding.textViewBansosExpiryDate
                    val bansosDescriptionView: TextView = binding.textViewBansosDescription

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

            val toFeedbackBtn: Button = binding.btnToFeedback
            toFeedbackBtn.setOnClickListener {
                val intent = Intent(this, FeedbackActivity::class.java)
                intent.putExtra("bansosId", bansosId)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}