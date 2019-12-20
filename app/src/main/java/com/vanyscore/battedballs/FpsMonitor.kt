package com.vanyscore.battedballs

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log

class FpsMonitor : GameObject() {

    private var fps = 60
    private var fpsCounter = 0
    private var currentTimeInMills = System.currentTimeMillis()
    private val difference = 1000L
    private var canDraw = false

    private var x = GameApp.parseIntoPx(20f)
    private var y = GameApp.parseIntoPx(20f)

    private var paint : Paint = Paint().also {
        it.color = Color.YELLOW
        it.textSize = GameApp.parseIntoPx(18f)
    }

    init {
        y += paint.textSize
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText("FPS: [$fps]", x, y, paint)
    }

    override fun update() {

        fpsCounter++

        if (System.currentTimeMillis() - currentTimeInMills >= difference) {
            restoreFramesCounter()
        }
    }

    private fun restoreFramesCounter() {
        fps = fpsCounter
        currentTimeInMills = System.currentTimeMillis()
        fpsCounter = 0
        canDraw = false
    }
}