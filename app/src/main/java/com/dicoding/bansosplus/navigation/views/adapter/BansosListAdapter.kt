package com.dicoding.bansosplus.navigation.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.bansosplus.R
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import java.util.Locale
import java.text.SimpleDateFormat

class BansosListAdapter(
    private var list: ArrayList<BansosItem>,
    private val context: Context,
) : RecyclerView.Adapter<BansosListAdapter.BansosHolder>() {


    class BansosHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var bansosNameTextView: TextView? = null
        private var bansosTypeTextView: TextView? = null
        private var bansosExpiryDateView: TextView? = null

        init {
            bansosNameTextView = view.findViewById(R.id.bansosName)
            bansosTypeTextView = view.findViewById(R.id.bansosType)
            bansosExpiryDateView = view.findViewById(R.id.expiryDateText)
        }

        fun bind(data: BansosItem) {
            bansosNameTextView?.text = data.name
            bansosTypeTextView?.text = data.type
            data.expiryDate?.let { date ->
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(date)
                bansosExpiryDateView?.text = formattedDate
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BansosHolder {
        return BansosHolder(LayoutInflater.from(parent.context).inflate(R.layout.bansos_card, parent, false))
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
}