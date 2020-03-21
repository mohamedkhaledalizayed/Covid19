package com.itgds.covid19.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itgds.covid19.R
import dagger.android.support.DaggerAppCompatActivity

class CountryDetailsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
    }
}
