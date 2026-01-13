package com.example.triptrack_ai.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triptrack_ai.R
import com.example.triptrack_ai.model.ThreatItem
import com.example.triptrack_ai.model.ThreatType

class ThreatsAdapter(private var items: List<ThreatItem>) : RecyclerView.Adapter<ThreatsAdapter.ThreatViewHolder>() {

    class ThreatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvBadge: TextView = itemView.findViewById(R.id.tvBadge)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_threat, parent, false)
        return ThreatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThreatViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        holder.tvTimestamp.text = item.timestamp
        holder.ivIcon.setImageResource(item.iconResId)

        // Reset badge style just in case
        holder.tvBadge.visibility = View.VISIBLE
        
        when (item.type) {
            ThreatType.PHISHING -> {
                holder.tvBadge.text = "Phishing"
                holder.tvBadge.setTextColor(Color.parseColor("#D32F2F")) // Red
                holder.tvBadge.setBackgroundResource(R.drawable.bg_badge_phishing)
                holder.ivIcon.setColorFilter(Color.parseColor("#D32F2F"))
                holder.ivIcon.setBackgroundResource(R.drawable.bg_circle_light) // You might want tinted backgrounds later
            }
            ThreatType.SUSPICIOUS -> {
                holder.tvBadge.text = "Suspicious"
                holder.tvBadge.setTextColor(Color.parseColor("#F57C00")) // Orange
                handleBadgeColor(holder.tvBadge, "#FFF3E0")
                holder.ivIcon.setColorFilter(Color.parseColor("#F57C00"))
            }
            ThreatType.WARNING -> {
                holder.tvBadge.text = "Warning"
                holder.tvBadge.setTextColor(Color.parseColor("#FFB300")) // Amber
                handleBadgeColor(holder.tvBadge, "#FFF8E1")
                holder.ivIcon.setColorFilter(Color.parseColor("#FFB300"))
            }
            ThreatType.SAFE -> {
                holder.tvBadge.text = "Safe"
                holder.tvBadge.setTextColor(Color.parseColor("#388E3C")) // Green
                handleBadgeColor(holder.tvBadge, "#E8F5E9")
                holder.ivIcon.setColorFilter(Color.parseColor("#388E3C"))
            }
        }
    }
    
    // Helper to tint background safely
    private fun handleBadgeColor(view: View, colorHex: String) {
        val drawable = view.context.getDrawable(R.drawable.bg_badge_phishing)?.mutate()
        drawable?.setTint(Color.parseColor(colorHex))
        view.background = drawable
    }

    override fun getItemCount() = items.size

    fun updateList(newItems: List<ThreatItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
