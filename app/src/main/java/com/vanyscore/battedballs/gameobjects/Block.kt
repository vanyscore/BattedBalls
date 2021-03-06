package com.vanyscore.battedballs.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.Intersectable

class Block(x : Float, y : Float,
            width : Float, height : Float,
            private var strength : Int = 0) : Drawable, Intersectable {

    private val rect = RectF(x, y,
       x + width, y + height)

    private val paint : Paint = Paint().apply {
        color = Color.WHITE
    }

    private val strokePaint = Paint()

    init {
        updatePaint()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
        canvas.drawRect(rect, strokePaint)
    }

    override fun getRect(): RectF {
        return rect
    }

    fun tryToDestroy(): Boolean {
        return if (--strength <= 0) {
            true
        } else {
            updatePaint()

            false
        }
    }

    private fun updatePaint() {
        strokePaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 10f
            color = when (strength) {
                0 -> Color.BLACK
                1 -> Color.YELLOW
                2 -> Color.MAGENTA
                else -> Color.RED
            }
        }
    }
}