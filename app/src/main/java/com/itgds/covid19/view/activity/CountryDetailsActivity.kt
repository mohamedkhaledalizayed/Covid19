package com.itgds.covid19.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itgds.covid19.R
import com.itgds.covid19.services.response.CountriesStat
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_country_details.*

class CountryDetailsActivity : DaggerAppCompatActivity() {

    private val country by lazy {
        intent.getBundleExtra("EXTRA")?.get("COUNTRY") as CountriesStat
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        tv_country_title.text = country.countryName
        tv_total_recovered_value.text = country.totalRecovered
        tv_total_confirmed_value.text = country.cases
        tv_total_deceased_value.text = country.deaths
        tv_total_serious_value.text = country.seriousCritical
        tv_new_cases_value.text = country.newCases
        tv_new_deaths_value.text = country.newDeaths
        tv_active_cases_value.text = country.activeCases
        tv_percentage_value.text = country.totalCasesPer1mPopulation
    }
}
