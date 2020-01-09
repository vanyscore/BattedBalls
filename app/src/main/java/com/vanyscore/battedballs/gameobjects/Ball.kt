package com.vanyscore.battedballs.gameobjects

import android.graphics.*
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.Intersectable

class Ball(var x : Float, var y : Float,
           var radius : Float) : Drawable, Intersectable {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(x, y, radius, paint)
    }

    override fun getRect(): RectF {
        return RectF(x - radius, y - radius, x + radius, y + radius)
    }
}