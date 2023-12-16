package com.dicoding.bansosplus.navigation.views.history.detailHistory.feedback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityFeedbackBinding
import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.navigation.BottomNavActivity
import com.dicoding.bansosplus.repository.FeedbackRepository
import com.dicoding.bansosplus.repository.UserRepository
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

        if (intent.hasExtra("bansosId")) {
            val bansosId = intent.getStringExtra("bansosId")
            val desc = binding.etFeedback.text

            var finalRating = ""
            if (bansosId != null){
                lifecycleScope.launch() {
                    try {
                        val response = UserRepository(activitySessionManager).get()
                        if (response.isSuccessful){
                            val user = response.body()?.data
                            Log.i("BANSOS", "Get user successfully")

                            if (user != null){
                                var rating="0"
                                binding.apply {
                                    ivStarRate1.setOnClickListener{
                                        rating = ivStarRate1.contentDescription.toString()
                                        Log.i("rating1", rating)

                                    }
                                    ivStarRate2.setOnClickListener{
                                        rating = ivStarRate2.contentDescription.toString()
                                        Log.i("rating2", rating)

                                    }
                                    ivStarRate3.setOnClickListener{
                                        rating = ivStarRate3.contentDescription.toString()
                                        Log.i("rating3", rating)
                                    }
                                    ivStarRate4.setOnClickListener{
                                        rating = ivStarRate4.contentDescription.toString()
                                        Log.i("rating4", rating)
                                    }
                                    ivStarRate5.setOnClickListener{
                                        rating = ivStarRate5.contentDescription.toString()
                                        Log.i("rating5", rating)
                                    }
                                }
                                finalRating = rating
                                Log.d("id", bansosId.toString() )
                                Log.d("score", finalRating )
                                Log.d("desc", desc.toString() )
                            }

                        }else {
                            Log.d("id", bansosId.toString() )
                            Log.d("score", finalRating )
                            Log.d("desc", desc.toString() )
                            Log.e("BANSOS", "Response failed")
                        }
                    }catch (e: Exception) {
                        Log.e("BANSOS", "Connection failed")
                    }
                }

                val btnUploadFeedback: Button = binding.btnFeedback
                btnUploadFeedback.setOnClickListener{
                    lifecycleScope.launch() {
                        uploadFeedback(
                            bansosId.toInt(),
                            finalRating.toInt(),
                            desc.toString()
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
        bansos_id: Int,
        score: Int,
        decription: String
    ){
        val request = FeedbackRequest()
        request.bansosId = bansos_id
        request.score = score
        request.description = decription

        try {
            val response = FeedbackRepository(activitySessionManager).addFeedback(request)
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