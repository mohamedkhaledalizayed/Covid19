package com.itgds.covid19.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.itgds.covid19.R
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        val data = intent.getStringExtra("data")

        when (data) {
            "treatment" -> tv_info.setText(R.string.treatment_text)
            "symptoms" -> tv_info.setText(R.string.symptoms_text)
            "prevention" -> tv_info.setText(R.string.prevention_text)
        }
    }
}