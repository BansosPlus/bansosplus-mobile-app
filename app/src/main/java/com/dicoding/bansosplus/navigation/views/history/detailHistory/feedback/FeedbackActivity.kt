package com.dicoding.bansosplus.navigation.views.history.detailHistory.feedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityFeedbackBinding
import com.dicoding.bansosplus.models.auth.FeedbackRequest
import com.dicoding.bansosplus.navigation.data.model.AcceptedBansosItem
import com.dicoding.bansosplus.navigation.data.model.FeedbacksItem
import com.dicoding.bansosplus.repository.FeedbackRepository
import kotlinx.coroutines.launch

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    private lateinit var sessionManager: SessionManager
    private val feedbackRepository: FeedbackRepository = FeedbackRepository(sessionManager)
    private val _feedback = MutableLiveData<FeedbacksItem?>()
    val feedback: MutableLiveData<FeedbacksItem?>
        get() = _feedback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addButton: Button = binding.btnFeedback
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

                addButton.setOnClickListener{
                    if (bansosId != null) {
                        addFeedback(
                            bansosId = bansosId.toInt(),
                            score = rating.toInt(),
                            description = "Good Job",
                            )
                    }
                }
            }

            val finalRating = rating.toInt()

        }
    }

    private  fun addFeedback(bansosId: Int,score: Int, description: String) {
        val request = FeedbackRequest()
        request.bansosId = bansosId
        request.score = score
        request.description = description

        lifecycleScope.launch {
            val response = feedbackRepository.addFeedback(request)
            if (response.isSuccessful) {
                val feedback = response.body()?.data
                _feedback.value = feedback
                Log.i("FEEDBACK", "Add feedback succesfully")
            } else {
                //
            }
        }

    }

}