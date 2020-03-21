package com.itgds.covid19.viewmodel.mainviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itgds.covid19.repository.RemoteRepository
import com.itgds.covid19.services.response.AllCountryResponse
import com.itgds.covid19.services.response.totalnumber.TotalNumbersResponse
import com.itgds.covid19.utils.BaseSchedulerProvider
import com.itgds.covid19.utils.BaseViewModel
import com.itgds.covid19.utils.ViewState
import javax.inject.Inject
import io.reactivex.functions.Consumer
class MainViewModel @Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider , private val repository: RemoteRepository
) : BaseViewModel(baseSchedulerProvider) {

    fun getAllCountry(): LiveData<ViewState<AllCountryResponse>> {

        val moviesLiveData = MutableLiveData<ViewState<AllCountryResponse>>()

        execute(
            loadingConsumer = Consumer {
                moviesLiveData.postValue(
                    ViewState.loading()
                )
            },
            successConsumer = Consumer { movieItemList ->
                movieItemList?.let {
                    moviesLiveData.postValue(
                        ViewState.success(it)
                    )
                }
            },
            throwableConsumer = Consumer { throwable ->
                moviesLiveData.postValue(
                    ViewState.error(throwable.message)
                )
            },
            useCase = repository.getAllCountry()
        )

        return moviesLiveData
    }

    fun getTotal(): LiveData<ViewState<TotalNumbersResponse>> {

        val moviesLiveData = MutableLiveData<ViewState<TotalNumbersResponse>>()

        execute(
            loadingConsumer = Consumer {
                moviesLiveData.postValue(
                    ViewState.loading()
                )
            },
            successConsumer = Consumer { movieItemList ->
                movieItemList?.let {
                    moviesLiveData.postValue(
                        ViewState.success(it)
                    )
                }
            },
            throwableConsumer = Consumer { throwable ->
                moviesLiveData.postValue(
                    ViewState.error(throwable.message)
                )
            },
            useCase = repository.getTotal()
        )

        return moviesLiveData
    }
}