package com.vanyscore.battedballs

import android.app.Application
import android.util.DisplayMetrics
import android.util.TypedValue

class GameApp : Application() {

    override fun onCreate() {
        super.onCreate()

        displayMetrics = resources.displayMetrics
    }

    companion object {

        const val DEBUG_KEY = "GameApp.debug"

        private lateinit var displayMetrics: DisplayMetrics

        fun parseIntoPx(dp : Float) : Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
        }
    }
}