package com.dicoding.bansosplus.navigation.views.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryListAdapter(
    private var list: ArrayList<BansosStatusItem>,
    private val context: Context,
    private val onItemClickListener: (BansosStatusItem) -> Unit
) : RecyclerView.Adapter<HistoryListAdapter.HistoryHolder>(){
    class HistoryHolder(val view: View, private val onItemClickListener: (BansosStatusItem) -> Unit) : RecyclerView.ViewHolder(view) {
        private var bansosNameTextView: TextView? = null
        private var bansosStatusTextView: TextView? = null
        private var bansosStatusImageView: ImageView? = null
        private var bansosExpiryDateView: TextView? = null
        private var bansosImageUrl: ImageView? = null

        init {
            bansosNameTextView = view.findViewById(R.id.bansosName)
            bansosStatusTextView = view.findViewById(R.id.registrationStatus)
            bansosStatusImageView = view.findViewById(R.id.registrationStatus_level)
            bansosExpiryDateView = view.findViewById(R.id.expiryDateText)
            bansosImageUrl = view.findViewById(R.id.bansosImage)
        }

        fun bind(data: BansosStatusItem) {
            bansosNameTextView?.text = data.bansosName
            val cekStatus = data.status
            Log.d("cek status", cekStatus)
            if(cekStatus == "REJECTED"){
                bansosStatusTextView?.text = "DITOLAK"
                bansosStatusImageView?.setImageResource(R.drawable.ic_status_ditolak)
            }else if(cekStatus == "TAKEN"){
                bansosStatusTextView?.text = "DIAMBIL"
                bansosStatusImageView?.setImageResource(R.drawable.ic_status_diambil)
            }
            data.createdAt?.let { date ->
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(date)
                bansosExpiryDateView?.text = formattedDate
            }

            bansosImageUrl?.let {
                Glide.with(view.context)
                    .load(data.imageUrl)
                    .into(it)
            }

            view.setOnClickListener{
                onItemClickListener.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_bansos_card, parent, false)
        return HistoryHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun updateBansosRegistrationList(bansosRegistrationList: ArrayList<BansosStatusItem>) {
        list.clear()
        bansosRegistrationList.sortBy { it.id }
        list = bansosRegistrationList
        notifyDataSetChanged()
    }
    fun getSelectedItemId(position: Int): Int {
        return list[position].id
    }
}