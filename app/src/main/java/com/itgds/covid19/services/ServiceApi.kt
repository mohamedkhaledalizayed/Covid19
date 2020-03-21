package com.itgds.covid19.services

import com.itgds.covid19.services.response.AllCountryResponse
import com.itgds.covid19.services.response.totalnumber.TotalNumbersResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("cases_by_country.php")
    fun getAllCountry(): Flowable<AllCountryResponse>

    @GET("worldstat.php")
    fun getTotal(): Flowable<TotalNumbersResponse>


}