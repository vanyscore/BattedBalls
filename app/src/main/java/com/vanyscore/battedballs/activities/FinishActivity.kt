package com.vanyscore.battedballs.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vanyscore.battedballs.R
import com.vanyscore.battedballs.RequestContainer
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val isWon = intent.getBooleanExtra(RequestContainer
            .EXTRA_FINISH_STATE, false)

        if (isWon) {
            tv_finish_state.text = resources.getString(R.string.win)
            tv_finish_state.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
        } else {
            tv_finish_state.text = resources.getString(R.string.defeat)
            tv_finish_state.setTextColor(ContextCompat.getColor(this, R.color.colorRed))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorBlack)
        }
    }
}