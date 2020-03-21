package com.itgds.covid19.viewmodel.mainviewmodel

import androidx.lifecycle.ViewModel
import com.itgds.covid19.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsViewModel(viewModel: MainViewModel): ViewModel
}