package com.vanyscore.battedballs.main

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceView
import com.vanyscore.battedballs.GameFinishHandler

class GameSurfaceView(context : Context, attrs : AttributeSet) : SurfaceView(context, attrs) {

    private var gameController : GameThread? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val windowRect = Rect()
        (context as Activity).window.windowManager.defaultDisplay.getRectSize(windowRect)

        gameController = GameThread(holder) { isWon ->
            (context as GameFinishHandler).apply {
                if (isWon)
                    onWon()
                else
                    onDefeat()
            }
        }.also {
            setOnTouchListener(it)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        setOnTouchListener(null)
        gameController = null
    }
}