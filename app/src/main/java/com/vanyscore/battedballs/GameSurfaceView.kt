package com.vanyscore.battedballs

import android.content.Context
import android.view.SurfaceView

class GameSurfaceView(context : Context) : SurfaceView(context) {

    private var gameController : GameThreadController? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        gameController = GameThreadController(holder)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        gameController = null
    }
}