package com.dicoding.bansosplus.navigation.views.history.detailHistory.feedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton: ImageButton = binding.backButton
        backButton.setOnClickListener {
            finish()
        }

        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")

            var rating="0"
            binding.apply {
                ivStarRate1.setOnClickListener{
                    rating = ivStarRate1.contentDescription.toString()
                }
                ivStarRate2.setOnClickListener{
                    rating = ivStarRate2.contentDescription.toString()
                }
                ivStarRate3.setOnClickListener{
                    rating = ivStarRate3.contentDescription.toString()
                }
                ivStarRate4.setOnClickListener{
                    rating = ivStarRate4.contentDescription.toString()
                }
                ivStarRate5.setOnClickListener{
                    rating = ivStarRate5.contentDescription.toString()
                }
            }
            val finalRating = rating.toInt()




        }
    }
}