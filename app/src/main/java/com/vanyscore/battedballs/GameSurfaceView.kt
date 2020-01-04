package com.vanyscore.battedballs

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceView
import androidx.core.graphics.toRectF

class GameSurfaceView(context : Context, attrs : AttributeSet) : SurfaceView(context, attrs) {

    private var gameController : GameThreadController? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val windowRect = Rect()
        (context as Activity).window.windowManager.defaultDisplay.getRectSize(windowRect)

        gameController = GameThreadController(holder)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        gameController = null
    }
}