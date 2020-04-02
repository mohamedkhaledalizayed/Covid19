package com.itgds.covid19.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itgds.covid19.R
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        when (intent.getStringExtra("data")) {
            "treatment" -> {
                tv_info.setText(R.string.treatment_text)
                tv_title.text = "Treatment"
            }
            "symptoms" -> {
                tv_info.setText(R.string.treatment_text)
                tv_title.text = "Symptoms"
            }
            "prevention" -> {
                tv_info.setText(R.string.treatment_text)
                tv_title.text = "Prevention"
            }
        }
    }
}