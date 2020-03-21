package com.itgds.covid19.viewmodel.countrydetailsviewmodel

import androidx.lifecycle.ViewModel
import com.itgds.covid19.di.ViewModelKey
import com.itgds.covid19.viewmodel.mainviewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class CountryDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailsViewModel::class)
    abstract fun bindsViewModel(viewModel: CountryDetailsViewModel): ViewModel
}