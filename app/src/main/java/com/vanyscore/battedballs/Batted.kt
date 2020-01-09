package com.vanyscore.battedballs

import android.graphics.RectF

interface Batted {
    fun getDirection(rect: RectF): Direction

    enum class Direction {
        TOP, RIGHT, BOTTOM, LEFT
    }
}