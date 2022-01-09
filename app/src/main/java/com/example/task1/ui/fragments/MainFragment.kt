package com.example.task1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.response.Facility
import com.example.task1.response.FaclitiesResponse
import com.example.task1.R
import com.example.task1.ui.adapter.FacilitiesAdapter
import com.example.task1.ui.viewmodel.MainViewModel

class MainFragment : Fragment(), FacilitiesAdapter.OnItemClickListener {

    var facilitiesResponse: FaclitiesResponse? = null
    var facilities = ArrayList<Facility>()
    lateinit var facilitiesAdapter: FacilitiesAdapter
    lateinit var mainFragmentViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeLiveData()
        mainFragmentViewModel.getData()
    }

    private fun setUpRecyclerView() {
        facilitiesAdapter = FacilitiesAdapter(context, facilities, this)
        val rvPropertyType = requireActivity().findViewById<RecyclerView>(R.id.rvPropertyType)
        rvPropertyType.apply {
            adapter = facilitiesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun observeLiveData() {
        mainFragmentViewModel.facilitiesResponse.observe(viewLifecycleOwner, {
            it?.let {
                facilitiesResponse = it
                it.facilities[0].visible = true
                it.facilities[0].options.forEach {
                    it.visible = true
                }
                facilities.addAll(it.facilities)
                setUpRecyclerView()
                facilitiesAdapter.notifyDataSetChanged()
            }
        })

    }

    override fun onItemClick(selectedFacility: Facility, position: Int, facilityPosition: Int) {
        var facilityCounter = 0
        facilities.forEach { facility ->
            facilityCounter ++
            facility.options.forEach {
                if (it.selected) {
                    var applicableExclusion = false
                    facilitiesResponse?.exclusions?.forEach { exclusions ->
                        if (facility.facilityId == exclusions[0].facilityId && it.id == exclusions[0].optionsId) {
                            applicableExclusion = true
                            facilities.forEach { facility ->

                                if (facility.facilityId == exclusions[1].facilityId) {
                                    facility.options.forEach { option ->
                                        option.visible = option.id != exclusions[1].optionsId
                                    }
                                }
                            }
                        }
                    }
                    if(!applicableExclusion && facilityCounter == 1){
                        for(i in 1 until facilities.size){
                            for(j in facilities[i].options.indices){
                                facilities[i].options[j].visible = true
                            }
                        }
                    }
                }
            }
        }
        for (i in 0 until facilities.size) {
            facilities[i].visible = i <= facilityPosition + 1
        }
        facilitiesAdapter.notifyDataSetChanged()
    }
}

