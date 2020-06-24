package com.vanyscore.battedballs

import android.graphics.RectF

fun RectF.intersects(rect : RectF) : Boolean {
    return this.intersects(rect.left, rect.top, rect.right, rect.bottom)
}