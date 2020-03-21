package com.itgds.covid19.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.itgds.covid19.R
import com.itgds.covid19.viewmodel.countrydetailsviewmodel.CountryDetailsViewModel
import com.itgds.covid19.viewmodel.mainviewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.itgds.covid19.services.response.AllCountryResponse
import com.itgds.covid19.services.response.CountriesStat
import com.itgds.covid19.services.response.totalnumber.TotalNumbersResponse
import com.itgds.covid19.utils.ViewState
import com.itgds.covid19.view.adapter.CountryRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        configViewModel()
        getAllCountry()
        getTotal()
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
                        Log.e("data", movieViewState.data.toString())
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

//                        homeMovieAdapter.submitList(movieViewState.data)
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
        } else if(errorMessage == null) {
            pb_home.visibility = View.GONE
            lt_home.visibility = View.VISIBLE
        }else{
            pb_home.visibility = View.GONE
            lt_home.visibility = View.GONE
            Toast.makeText(this,"An error Occurred!",Toast.LENGTH_LONG).show()
        }
    }
}

