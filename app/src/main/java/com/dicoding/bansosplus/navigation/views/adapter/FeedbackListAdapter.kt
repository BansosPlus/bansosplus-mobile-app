package com.dicoding.bansosplus.navigation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.navigation.data.model.FeedbackItem


class FeedbackListAdapter(
    private var list: ArrayList<FeedbackItem>,
    private val context: Context,
) : RecyclerView.Adapter<FeedbackListAdapter.FeedbackHolder>(){

    class FeedbackHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var feedbackUsernameTextView: TextView? = null
        private var feedbackScoreTextView: TextView? = null
        private var feedbackDescriptionTextView: TextView? = null

        init {
            feedbackUsernameTextView = view.findViewById(R.id.feedbackUserName)
            feedbackScoreTextView = view.findViewById(R.id.feedbackScore)
            feedbackDescriptionTextView = view.findViewById(R.id.feedbackDescription)
        }

        fun bind(data: FeedbackItem) {
            feedbackUsernameTextView?.text = data.userId.toString()
            feedbackScoreTextView?.text = data.score.toString()
            feedbackDescriptionTextView?.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackHolder {
        return FeedbackHolder(LayoutInflater.from(parent.context).inflate(R.layout.feedback_card, parent, false))
    }

    override fun onBindViewHolder(holder: FeedbackHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun updateFeedbackList(feedbackList: ArrayList<FeedbackItem>) {
        list.clear()
        feedbackList.sortBy { it.id }
        list = feedbackList
        notifyDataSetChanged()
    }
    fun getSelectedItemId(position: Int): Int {
        return list[position].id
    }
}