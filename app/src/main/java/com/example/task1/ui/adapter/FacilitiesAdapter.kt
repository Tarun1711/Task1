package com.example.task1.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.response.Facility
import com.example.task1.R

class FacilitiesAdapter(private var mContext: Context?, private var facilities:List<Facility>, private var mListener: OnItemClickListener) : RecyclerView.Adapter<FacilitiesAdapter.ItemViewHolder>(), OptionsAdapter.OnItemClickListener {

    lateinit var optionsAdapter: OptionsAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.facilities_item, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvOptions = itemView.findViewById<RecyclerView>(R.id.rvOptions)
        val tvFacilities = itemView.findViewById<TextView>(R.id.tvFacilities)

    }

    interface OnItemClickListener {
        fun onItemClick(selectedFacility: Facility, position: Int, facilityPosition: Int)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        optionsAdapter = OptionsAdapter(mContext, facilities[position], this, position)

        if(facilities[position].visible) {
            holder.tvFacilities.text = facilities[position].name
            holder.rvOptions.apply {
                adapter = optionsAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
        }
    }

    override fun getItemCount(): Int {
        var c = 0
        if(facilities.isNotEmpty() ) {
            facilities.forEach {
                if(it.visible){
                   c++
                }
            }
        }
        return c
    }

    override fun onItemClick(facility: Facility, position: Int, facilityPosition:Int) {
        mListener.onItemClick(facility, position, facilityPosition)
    }
}