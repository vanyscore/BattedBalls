package com.vanyscore.battedballs

import android.graphics.*
import android.view.SurfaceHolder
import com.vanyscore.battedballs.controllers.BallController
import com.vanyscore.battedballs.gameobjects.Ball
import com.vanyscore.battedballs.gameobjects.Borders
import com.vanyscore.battedballs.gameobjects.FpsMonitor

class GameRunnable(private val mainHolder : SurfaceHolder) : Runnable {

    private val drawables : MutableList<Drawable> = mutableListOf()
    private val updatable : MutableList<Updatable> = mutableListOf()

    init {
        val fpsMonitor = FpsMonitor()
        val ball = Ball(GameApp.getDisplayMetrics().widthPixels / 2f,
            GameApp.getDisplayMetrics().heightPixels / 2f,
            20f)
        val borders = Borders()

        drawables.add(fpsMonitor)
        drawables.add(ball)
        drawables.add(borders)

        updatable.add(fpsMonitor)
        updatable.add(BallController(ball, borders))
    }

    override fun run() {
        while (!Thread.interrupted()) {
            val canvas = mainHolder.lockCanvas() ?: continue

            canvas.drawColor(Color.BLACK)

            updatable.forEach {
                it.update()
            }

            drawables.forEach {
                it.draw(canvas)
            }

            mainHolder.unlockCanvasAndPost(canvas)
        }
    }
}