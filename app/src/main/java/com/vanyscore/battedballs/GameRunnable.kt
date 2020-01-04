package com.vanyscore.battedballs

import android.graphics.*
import android.util.Log
import android.view.SurfaceHolder

class GameRunnable(private val mainHolder : SurfaceHolder) : Runnable {

    private val gameObjects : MutableList<GameObject> = mutableListOf()

    init {
        gameObjects.add(FpsMonitor())
        gameObjects.add(Borders())
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