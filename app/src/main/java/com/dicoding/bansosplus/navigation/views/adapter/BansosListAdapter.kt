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
import java.util.Locale
import java.text.SimpleDateFormat

class BansosListAdapter(
    private var list: ArrayList<BansosItem>,
    private val context: Context,
    private val onItemClickListener: (BansosItem) -> Unit
) : RecyclerView.Adapter<BansosListAdapter.BansosHolder>() {


    class BansosHolder(val view: View, private val onItemClickListener: (BansosItem) -> Unit) : RecyclerView.ViewHolder(view) {
        private var bansosNameTextView: TextView? = null
        private var bansosExpiryDateView: TextView? = null
        private var bansosImageUrl: ImageView? = null

        init {
            bansosNameTextView = view.findViewById(R.id.bansosName)
            bansosExpiryDateView = view.findViewById(R.id.expiryDateText)
            bansosImageUrl = view.findViewById(R.id.bansosImage)
        }

        fun bind(data: BansosItem) {
            bansosNameTextView?.text = data.name
            data.expiryDate?.let { date ->
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BansosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bansos_card, parent, false)
        return BansosHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: BansosHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    fun updateBansosList(bansosList: ArrayList<BansosItem>) {
        list.clear()
        bansosList.sortBy { it.name }
        list = bansosList
        notifyDataSetChanged()
    }
    fun getSelectedItemId(position: Int): Int {
        return list[position].id
    }
}