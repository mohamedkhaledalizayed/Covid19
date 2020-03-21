package com.itgds.covid19.di


import android.app.Application
import com.itgds.covid19.app.Covid19
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ViewModelFactory::class,
            ActivityBuildersModule::class,
            AppModule::class
            ]
)
interface AppComponent : AndroidInjector<Covid19> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}