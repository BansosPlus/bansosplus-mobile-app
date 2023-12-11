package com.dicoding.bansosplus.navigation.views.home.detailBansos

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.navigation.data.model.FeedbackItem
import com.dicoding.bansosplus.navigation.views.adapter.FeedbackListAdapter
import java.text.SimpleDateFormat
import java.util.Locale


class DetailBansosActivity : AppCompatActivity() {

    private lateinit var activitySessionManager: SessionManager
    private lateinit var viewModel: DetailBansosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bansos)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
        val intent = intent
        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")

            viewModel = ViewModelProvider(this, DetailBansosViewModelFactory(activitySessionManager)).get(DetailBansosViewModel::class.java)

            if (bansosId != null) {
                viewModel.get(bansosId)
            }

            viewModel.bansosItem.observe(this, Observer { bansosDetails ->

                if (bansosDetails != null) {

                    val bansosImageView: ImageView = findViewById(R.id.bansosImageView)
                    val bansosTitleView: TextView = findViewById(R.id.textViewBansosTitle)
                    val bansosExpiryDateView: TextView = findViewById(R.id.textViewBansosExpiryDate)
                    val bansosDescriptionView: TextView = findViewById(R.id.textViewBansosDescription)
                    val bansosFeedbackListView: RecyclerView = findViewById(R.id.listFeedbackView)

                    bansosTitleView.text = bansosDetails.name
                    bansosDetails.expiryDate?.let { value ->
                        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(value)
                        bansosExpiryDateView?.text = formattedDate
                    }
                    bansosDescriptionView.text = bansosDetails.description
                    Glide.with(this)
                        .load(bansosDetails.imageUrl)
                        .into(bansosImageView)

                    val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    bansosFeedbackListView.setHasFixedSize(true)
                    bansosFeedbackListView.layoutManager = layoutManager
                    bansosFeedbackListView.isNestedScrollingEnabled = false
                    val recyclerViewLayoutParams = bansosFeedbackListView.layoutParams
                    bansosFeedbackListView.layoutParams = recyclerViewLayoutParams

                    val adapter = FeedbackListAdapter(ArrayList(), this)
                    bansosFeedbackListView.adapter = adapter
                    val feedbackListUpdateObserver: Observer<ArrayList<FeedbackItem>> =
                        Observer<ArrayList<FeedbackItem>> { feedbackList ->
                            adapter.updateFeedbackList(feedbackList)
                        }

                    viewModel.feedbackList.observe(this, feedbackListUpdateObserver)
                    viewModel.getFeedback(bansosDetails.id)

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