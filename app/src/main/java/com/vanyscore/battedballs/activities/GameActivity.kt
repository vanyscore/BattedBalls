package com.vanyscore.battedballs.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.GameFinishHandler
import com.vanyscore.battedballs.R
import com.vanyscore.battedballs.RequestContainer

class GameActivity : AppCompatActivity(), GameFinishHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GameApp.init(resources.displayMetrics)

        setContentView(R.layout.activity_game)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorWhite)
        }
    }

    override fun onWon() {
        setResult(RequestContainer.RESULT_WON)
        finish()
    }

    override fun onDefeat() {
        setResult(RequestContainer.RESULT_DEFEAT)
        finish()
    }
}
