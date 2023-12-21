package com.dicoding.bansosplus.navigation.views.home.detailBansos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.ActivityDetailBansosBinding
import com.dicoding.bansosplus.navigation.data.model.FeedbackItem
import com.dicoding.bansosplus.navigation.views.adapter.FeedbackListAdapter
import com.dicoding.bansosplus.navigation.views.home.detailBansos.pengajuanBansos.PengajuanBansosActivity
import java.text.SimpleDateFormat
import java.util.Locale


class DetailBansosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBansosBinding
    private lateinit var activitySessionManager: SessionManager
    private lateinit var viewModel: DetailBansosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBansosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activitySessionManager = SessionManager(this)

        val backButton: ImageButton = binding.backButton
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

                    val bansosImageView: ImageView = binding.bansosImageView
                    val bansosTitleView: TextView = binding.textViewBansosTitle
                    val bansosExpiryDateView: TextView = binding.textViewBansosExpiryDate
                    val bansosDescriptionView: TextView = binding.textViewBansosDescription
                    val bansosFeedbackListView: RecyclerView = binding.listFeedbackView

                    bansosTitleView.text = bansosDetails.name
                    bansosDetails.expiryDate.let { value ->
                        val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(value)
                        bansosExpiryDateView.text = formattedDate
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
                    Toast.makeText(this, getString(R.string.failed_get_detail), Toast.LENGTH_SHORT).show()
                    finish()
                }
            })

            val daftarButton: Button = binding.daftarButton
            daftarButton.setOnClickListener {
                val intent = Intent(this, PengajuanBansosActivity::class.java)
                intent.putExtra("bansosId", bansosId)
                startActivity(intent)
            }
        } else {
            Toast.makeText(this, getString(R.string.no_bansos_id), Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}