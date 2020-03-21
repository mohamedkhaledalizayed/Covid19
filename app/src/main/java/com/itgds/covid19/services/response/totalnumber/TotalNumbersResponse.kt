package com.itgds.covid19.services.response.totalnumber


import com.google.gson.annotations.SerializedName

data class TotalNumbersResponse(
    @SerializedName("new_cases")
    val newCases: String,
    @SerializedName("new_deaths")
    val newDeaths: String,
    @SerializedName("statistic_taken_at")
    val statisticTakenAt: String,
    @SerializedName("total_cases")
    val totalCases: String,
    @SerializedName("total_deaths")
    val totalDeaths: String,
    @SerializedName("total_recovered")
    val totalRecovered: String
)