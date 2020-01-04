package com.vanyscore.battedballs

import android.graphics.*
import android.util.Log

class Borders : GameObject() {

    private val paint = Paint().apply {
        color = Color.WHITE
    }

    private val size = GameApp.parseIntoPx(30f).toInt()

    private val rectangles = listOf(
        Rect(0, 0, size, GameApp.getDisplayMetrics().heightPixels),
        Rect(0, 0, GameApp.getDisplayMetrics().widthPixels, size),
        Rect(GameApp.getDisplayMetrics().widthPixels - size, 0,
            GameApp.getDisplayMetrics().widthPixels, GameApp.getDisplayMetrics().heightPixels)
    )

    init {
        Log.d(GameApp.DEBUG_KEY, "x: ${rectangles[2].left} y: ${rectangles[2].top}" +
                " w: ${rectangles[2].right} h: ${rectangles[2].height()}")
    }

    override fun draw(canvas: Canvas) {
        rectangles.forEach {
            canvas.drawRect(it, paint)
        }
    }

    override fun update() {
        // Nothing
    }
}