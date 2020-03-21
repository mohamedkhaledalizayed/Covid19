package com.itgds.covid19.repository

import com.itgds.covid19.services.ServiceApi
import com.itgds.covid19.services.response.AllCountryResponse
import com.itgds.covid19.services.response.totalnumber.TotalNumbersResponse
import io.reactivex.Flowable
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val serviceApi: ServiceApi
) {

    fun getAllCountry(): Flowable<AllCountryResponse> {
        return serviceApi.getAllCountry()
    }

    fun getTotal(): Flowable<TotalNumbersResponse> {
        return serviceApi.getTotal()
    }

}