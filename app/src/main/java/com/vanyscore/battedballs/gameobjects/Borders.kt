package com.vanyscore.battedballs.gameobjects

import android.graphics.*
import androidx.core.graphics.toRectF
import com.vanyscore.battedballs.Batted
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.GameApp

class Borders : Drawable, Batted {

    private val paint = Paint().apply {
        color = Color.WHITE
    }

    private val size = GameApp.parseIntoPx(30f).toInt()

    private val rectangles = listOf(
        Rect(0, 0, size, GameApp.getDisplayMetrics().heightPixels).toRectF(),
        Rect(0, 0, GameApp.getDisplayMetrics().widthPixels, size).toRectF(),
        Rect(
            GameApp.getDisplayMetrics().widthPixels - size, 0,
            GameApp.getDisplayMetrics().widthPixels, GameApp.getDisplayMetrics().heightPixels).toRectF(),
        Rect(0, GameApp.getDisplayMetrics().heightPixels - size * 2,
            GameApp.getDisplayMetrics().widthPixels, GameApp.getDisplayMetrics().heightPixels).toRectF()
    )

    fun getBorders() : List<RectF> = rectangles

    override fun draw(canvas: Canvas) {
        rectangles.forEach {
            canvas.drawRect(it, paint)
        }
    }

    override fun getDirection(rect: RectF): Batted.Direction {
        rectangles.forEach {
            if (rect.intersects(it.left, it.top, it.right, it.bottom)) {
                return when (rectangles.indexOf(it)) {
                    0 -> Batted.Direction.LEFT
                    1 -> Batted.Direction.TOP
                    2 -> Batted.Direction.RIGHT
                    else -> Batted.Direction.BOTTOM
                }
            }
        }

        return Batted.Direction.BOTTOM
    }
}