package com.itgds.covid19.view.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.itgds.covid19.R
import com.itgds.covid19.services.response.AllCountryResponse
import com.itgds.covid19.services.response.totalnumber.TotalNumbersResponse
import com.itgds.covid19.utils.ViewState
import com.itgds.covid19.viewmodel.mainviewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configViewModel()
        getAllCountry()
        getTotal()
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
                        Log.e("data",movieViewState.data.toString())
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
                        setVisibility(isLoading = false)
                        Log.e("total",movieViewState.data.toString())
//                        homeMovieAdapter.submitList(movieViewState.data)
                    }

                    ViewState.Status.ERROR -> {
                        setVisibility(isLoading = false, errorMessage = movieViewState.message)
                    }
                }
            }
        )
    }

    fun setVisibility(isLoading: Boolean, errorMessage: String? = null) {

    }
}
