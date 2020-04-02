package com.itgds.covid19.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itgds.covid19.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        cv_statistics.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, MainActivity::class.java))
        }
        cv_treatment.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            intent.putExtra("data", "treatment")
            startActivity(intent)
        }
        cv_prevention.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            intent.putExtra("data", "prevention")
            startActivity(intent)
        }
        cv_symptoms.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            intent.putExtra("data", "symptoms")
            startActivity(intent)
        }
        cv_emergency_numbers.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, EmergencyActivity::class.java))
        }
    }
}
