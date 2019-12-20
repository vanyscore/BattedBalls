package com.vanyscore.battedballs

import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.Log
import android.view.SurfaceHolder

class GameRunnable(private val mainHolder : SurfaceHolder) : Runnable {

    private val gameObjects : MutableList<GameObject> = mutableListOf()

    init {
        gameObjects.add(FpsMonitor())
    }

    override fun run() {
        while (!Thread.interrupted()) {
            val canvas = mainHolder.lockCanvas() ?: continue

            canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)

            gameObjects.forEach {
                it.draw(canvas)
                it.update()
            }

            mainHolder.unlockCanvasAndPost(canvas)
        }
    }
}