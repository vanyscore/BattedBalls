package com.vanyscore.battedballs.gameobjects

import android.graphics.*
import androidx.core.graphics.toRectF
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.Intersectable

class Border(private val rect : RectF)
    : Drawable, Intersectable {

    private val paint = Paint().apply {
        color = Color.WHITE
    }

    override fun getRect(): RectF {
        return rect
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }
}