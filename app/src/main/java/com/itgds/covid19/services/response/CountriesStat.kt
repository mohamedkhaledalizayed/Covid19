package com.itgds.covid19.services.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountriesStat(
    @SerializedName("active_cases")
    val activeCases: String,
    @SerializedName("cases")
    val cases: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("deaths")
    val deaths: String,
    @SerializedName("new_cases")
    val newCases: String,
    @SerializedName("new_deaths")
    val newDeaths: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("serious_critical")
    val seriousCritical: String,
    @SerializedName("total_cases_per_1m_population")
    val totalCasesPer1mPopulation: String,
    @SerializedName("total_recovered")
    val totalRecovered: String
) : Parcelable