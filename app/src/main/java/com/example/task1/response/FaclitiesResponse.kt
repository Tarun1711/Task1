package com.example.task1.response


import com.google.gson.annotations.SerializedName

data class FaclitiesResponse(
    @SerializedName("exclusions")
    val exclusions: List<List<Exclusion>>,
    @SerializedName("facilities")
    val facilities: List<Facility>
)

data class Option(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    var selected: Boolean = false,
    var visible: Boolean = true
)

data class Facility(
    @SerializedName("facility_id")
    val facilityId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("options")
    val options: List<Option>,
    var visible: Boolean = false
)

data class Exclusion(
    @SerializedName("facility_id")
    val facilityId: String,
    @SerializedName("options_id")
    val optionsId: String
)