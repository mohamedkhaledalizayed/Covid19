package com.itgds.covid19.utils

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

}