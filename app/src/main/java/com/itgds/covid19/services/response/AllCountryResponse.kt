package com.itgds.covid19.services.response


import com.google.gson.annotations.SerializedName

data class AllCountryResponse(
    @SerializedName("countries_stat")
    val countriesStat: List<CountriesStat>,
    @SerializedName("statistic_taken_at")
    val statisticTakenAt: String
)