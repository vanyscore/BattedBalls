package com.vanyscore.battedballs.gameobjects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.Intersectable

class Platform : Drawable, Intersectable {

    private val paint = Paint().apply {
        color = Color.WHITE
    }
    private lateinit var rect : RectF

    override fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    fun initRect(rect : RectF) {
        this.rect = rect
    }

    override fun getRect(): RectF = rect
}