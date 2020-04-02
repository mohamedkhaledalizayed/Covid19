package com.itgds.covid19.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.itgds.covid19.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Glide.with(this).load(R.raw.virus).into(img);

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, DashboardActivity::class.java))
            finish()
        }, 2000)
    }
}
