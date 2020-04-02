package com.itgds.covid19.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.itgds.covid19.R
import kotlinx.android.synthetic.main.activity_information.*

class EmergencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)
        }

    fun call(view: View) {
        val call= Intent(Intent.ACTION_DIAL)
        call.data = Uri.parse("tel:105")
        startActivity(call)
    }
}
