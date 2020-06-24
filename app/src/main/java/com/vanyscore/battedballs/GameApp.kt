package com.vanyscore.battedballs

import android.app.Application
import android.util.DisplayMetrics
import android.util.TypedValue

class GameApp : Application() {

    companion object {

        const val DEBUG_KEY = "GameApp.debug"

        lateinit var screenSize : Pair<Float, Float>

        var screenDensity : Float = 1f
        var screenWidth : Float = 1f
        var screenHeight : Float = 1f

        fun parseIntoPx(value : Float) : Float {
            return value * screenDensity
        }

        fun init(displayMetrics: DisplayMetrics) {
            screenDensity = displayMetrics.density
            screenWidth = displayMetrics.widthPixels.toFloat()
            screenHeight = displayMetrics.heightPixels.toFloat()

            screenSize = screenWidth to screenHeight
        }
    }
}