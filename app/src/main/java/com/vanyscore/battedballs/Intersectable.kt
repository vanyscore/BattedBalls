package com.vanyscore.battedballs

import android.graphics.RectF

interface Intersectable {
    fun getRect() : RectF

    enum class Direction {
        RIGHT, BOTTOM, LEFT, TOP
    }
}