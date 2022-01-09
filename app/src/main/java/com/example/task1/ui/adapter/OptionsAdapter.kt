package com.example.task1.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.response.Facility
import com.example.task1.R

class OptionsAdapter(
    private var mContext: Context?,
    private var facility: Facility,
    private var mListener: OnItemClickListener,
    private val facilityPosition: Int
) : RecyclerView.Adapter<OptionsAdapter.ItemViewHolder>() {

    private var totalCount = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.options_item, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rbFacility = itemView.findViewById<RadioButton>(R.id.rbOption)
    }

    interface OnItemClickListener {
        fun onItemClick(facility: Facility, position: Int, facilityPosition: Int)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.rbFacility.text = facility.options[position].name

        if(facility.options[position].selected){
            holder.rbFacility.isChecked = true
        }

        if (!facility.options[position].visible) {
            holder.rbFacility.background.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
            holder.rbFacility.isEnabled = false
        }else {
            holder.rbFacility.background.setColorFilter(null)

            holder.rbFacility.setOnClickListener {
                facility.options.forEach { option ->
                    option.selected = option.id == facility.options[position].id
                }
                mListener.onItemClick(facility, position, facilityPosition)
                notifyDataSetChanged()
            }
        }

    }

    override fun getItemCount(): Int {
        return facility.options.size
    }
}