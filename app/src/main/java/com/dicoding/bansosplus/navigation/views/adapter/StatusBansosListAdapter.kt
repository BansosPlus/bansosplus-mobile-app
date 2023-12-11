package com.dicoding.bansosplus.navigation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import java.text.SimpleDateFormat
import java.util.Locale

class StatusBansosListAdapter(
    private var list: ArrayList<BansosStatusItem>,
    private val context: Context,
    private val onItemClickListener: (BansosStatusItem) -> Unit
) : RecyclerView.Adapter<StatusBansosListAdapter.StatusBansosHolder>() {

    class StatusBansosHolder(val view: View, private val onItemClickListener: (BansosStatusItem) -> Unit) : RecyclerView.ViewHolder(view) {
        private var bansosNameTextView: TextView? = null
        private var bansosStatusTextView: TextView? = null
        private var bansosExpiryDateView: TextView? = null
        private var bansosImageUrl: ImageView? = null

        init {
            bansosNameTextView = view.findViewById(R.id.bansosName)
            bansosStatusTextView = view.findViewById(R.id.registrationStatus)
            bansosExpiryDateView = view.findViewById(R.id.expiryDateText)
            bansosImageUrl = view.findViewById(R.id.bansosImage)
        }

        fun bind(data: BansosStatusItem) {
            bansosNameTextView?.text = data.bansosName
            bansosStatusTextView?.text = data.status

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusBansosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.status_bansos_card, parent, false)
        return StatusBansosHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: StatusBansosHolder, position: Int) {
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