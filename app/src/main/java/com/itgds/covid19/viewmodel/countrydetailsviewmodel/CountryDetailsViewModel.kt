package com.itgds.covid19.viewmodel.countrydetailsviewmodel

import com.itgds.covid19.repository.RemoteRepository
import com.itgds.covid19.utils.BaseSchedulerProvider
import com.itgds.covid19.utils.BaseViewModel
import javax.inject.Inject

class CountryDetailsViewModel @Inject
constructor(
    baseSchedulerProvider: BaseSchedulerProvider, repository: RemoteRepository
) : BaseViewModel(baseSchedulerProvider) {

}