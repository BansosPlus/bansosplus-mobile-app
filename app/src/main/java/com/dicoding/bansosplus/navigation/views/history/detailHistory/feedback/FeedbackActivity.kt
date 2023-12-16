package com.dicoding.bansosplus.navigation.views.history.detailHistory.feedback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityFeedbackBinding
import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.repository.FeedbackRepository
import kotlinx.coroutines.launch

class FeedbackActivity : AppCompatActivity() {

    private lateinit var activitySessionManager: SessionManager
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = binding.backButton
        backButton.setOnClickListener {
            finish()
        }

        val intent = intent
        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")
            var rating =""
            Log.d("FEEDBACK", "bansosId : ${bansosId}")
            if (bansosId != null){
                binding.apply {
                    ivStarRate1.setOnClickListener{
                        rating = ivStarRate1.contentDescription.toString()
                        ivStarRate1.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate2.setImageResource(R.drawable.ic_star_outline)
                        ivStarRate3.setImageResource(R.drawable.ic_star_outline)
                        ivStarRate4.setImageResource(R.drawable.ic_star_outline)
                        ivStarRate5.setImageResource(R.drawable.ic_star_outline)
                    }
                    ivStarRate2.setOnClickListener{
                        rating = ivStarRate2.contentDescription.toString()
                        ivStarRate1.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate2.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate3.setImageResource(R.drawable.ic_star_outline)
                        ivStarRate4.setImageResource(R.drawable.ic_star_outline)
                        ivStarRate5.setImageResource(R.drawable.ic_star_outline)
                    }
                    ivStarRate3.setOnClickListener{
                        rating = ivStarRate3.contentDescription.toString()
                        ivStarRate1.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate2.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate3.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate4.setImageResource(R.drawable.ic_star_outline)
                        ivStarRate5.setImageResource(R.drawable.ic_star_outline)
                    }
                    ivStarRate4.setOnClickListener{
                        rating = ivStarRate4.contentDescription.toString()
                        ivStarRate1.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate2.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate3.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate4.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate5.setImageResource(R.drawable.ic_star_outline)
                    }
                    ivStarRate5.setOnClickListener{
                        rating = ivStarRate5.contentDescription.toString()
                        ivStarRate1.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate2.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate3.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate4.setImageResource(R.drawable.ic_star_filled)
                        ivStarRate5.setImageResource(R.drawable.ic_star_filled)
                    }
                }

                val btnAddFeedback: Button = binding.btnFeedback
                btnAddFeedback.setOnClickListener{
                    lifecycleScope.launch {
                        uploadFeedback(
                            bansosId.toInt(),
                            rating.toInt(),
                            binding.etFeedback.text.toString()
                        )
                    }
                }
            }

        } else{
            Toast.makeText(this, "No bansosId found in the intent", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private suspend fun uploadFeedback(
        bansosId: Int,
        score: Int,
        description: String
    ){
        val request = FeedbackRequest()
        request.bansosId = bansosId
        request.score = score
        request.description = description
        Log.d("FEEDBACK PAGE", "bansos id : ${bansosId}")
        Log.d("FEEDBACK PAGE", "rating : ${score}")
        Log.d("FEEDBACK PAGE", "feedback description : ${description}")
        try {
            val response = FeedbackRepository(activitySessionManager).addFeedback(request)
            if (response.isSuccessful) {
                val feedback = response.body()?.data
                Log.i("FEEDBACK", "Register bansos successfully ${feedback}")
                val intent = Intent(this, BottomNavActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.e("FEEDBACK", "Response failed")
            }
        } catch (e: Exception) {
            Log.e("FEEDBACK", "Connection failed")
        }
    }

}