package com.vanyscore.battedballs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vanyscore.battedballs.BuildConfig
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GameApp.init(resources.displayMetrics)

        tv_version.text = "Version: ${BuildConfig.VERSION_NAME}"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlack)
        }

        tv_start_game.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }

}