package com.itgds.covid19.di

import com.itgds.covid19.view.activity.CountryDetailsActivity
import com.itgds.covid19.view.activity.MainActivity
import com.itgds.covid19.viewmodel.countrydetailsviewmodel.CountryDetailsViewModelModule
import com.itgds.covid19.viewmodel.mainviewmodel.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [CountryDetailsViewModelModule::class])
    abstract fun contributeCountryDetailsActivity(): CountryDetailsActivity


}