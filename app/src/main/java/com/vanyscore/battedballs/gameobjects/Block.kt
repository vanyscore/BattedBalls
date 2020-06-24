package com.vanyscore.battedballs.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.Intersectable

class Block(x : Float, y : Float,
            width : Float, height : Float) : Drawable, Intersectable {

    private val paint : Paint = Paint().apply {
        color = Color.WHITE
    }

    private val strokePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 10f
        color = Color.BLACK
    }

    private val rect = RectF(x, y,
        x + width, y + height)

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
        canvas.drawRect(rect, strokePaint)
    }

    override fun getRect(): RectF {
        return rect
    }
}