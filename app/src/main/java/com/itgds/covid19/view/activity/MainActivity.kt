package com.itgds.covid19.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.itgds.covid19.R
import com.itgds.covid19.viewmodel.mainviewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.itgds.covid19.services.response.AllCountryResponse
import com.itgds.covid19.services.response.CountriesStat
import com.itgds.covid19.services.response.totalnumber.TotalNumbersResponse
import com.itgds.covid19.utils.ViewState
import com.itgds.covid19.view.adapter.CountryRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var countries: List<CountriesStat> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(this).load(R.raw.notification).into(imageView);

        initView()
        configViewModel()
        getAllCountry()
        getTotal()
        listenOnSearch()
    }

    private fun listenOnSearch() {
        lt_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                filterCountries(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                filterCountries(p0)
                return true
            }

        })
    }

    private fun filterCountries(p0: String) {
        (rv_countries.adapter as CountryRecyclerViewAdapter).updateCountries(countries.filter {
            it.countryName.contains(
                p0,
                ignoreCase = true
            )
        })
    }

    private fun initView() {
        rv_countries.layoutManager = LinearLayoutManager(this)
    }

    private fun configViewModel() {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private fun getAllCountry() {
        mainViewModel.getAllCountry().observe(this,
            Observer<ViewState<AllCountryResponse>> { movieViewState ->
                when (movieViewState.status) {
                    ViewState.Status.LOADING -> {
                        setVisibility(isLoading = true)
                    }

                    ViewState.Status.SUCCESS -> {
                        setVisibility(isLoading = false)
                        countries = movieViewState.data?.countriesStat ?: listOf()
                        rv_countries.adapter = CountryRecyclerViewAdapter(
                            countries = movieViewState.data?.countriesStat ?: listOf()
                        ) {
                            Log.e("country clicked", it.toString())
                            var bundle = Bundle()
                            bundle.putParcelable("COUNTRY", it)
                            startActivity(
                                Intent(this, CountryDetailsActivity::class.java).putExtra(
                                    "EXTRA",
                                    bundle
                                )
                            )
                        }
                    }

                    ViewState.Status.ERROR -> {
                        setVisibility(isLoading = false, errorMessage = movieViewState.message)
                    }
                }
            }
        )
    }

    private fun getTotal() {
        mainViewModel.getTotal().observe(this,
            Observer<ViewState<TotalNumbersResponse>> { movieViewState ->
                when (movieViewState.status) {
                    ViewState.Status.LOADING -> {
                        setVisibility(isLoading = true)
                    }

                    ViewState.Status.SUCCESS -> {
                        tv_total_confirmed_value.text = movieViewState.data?.totalCases
                        tv_total_deceased_value.text = movieViewState.data?.totalDeaths
                        tv_total_recovered_value.text = movieViewState.data?.totalRecovered
                        setVisibility(isLoading = false)
                    }

                    ViewState.Status.ERROR -> {
                        setVisibility(isLoading = false, errorMessage = movieViewState.message)
                    }
                }
            }
        )
    }

    fun setVisibility(isLoading: Boolean, errorMessage: String? = null) {
        if (isLoading) {
            pb_home.visibility = View.VISIBLE
            lt_home.visibility = View.GONE
        } else if (errorMessage == null) {
            pb_home.visibility = View.GONE
            lt_home.visibility = View.VISIBLE
        } else {
            pb_home.visibility = View.GONE
            lt_home.visibility = View.GONE
            Toast.makeText(this, "An error Occurred!", Toast.LENGTH_LONG).show()
        }
    }
}

