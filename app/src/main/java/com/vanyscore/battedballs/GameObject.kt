package com.vanyscore.battedballs

import android.graphics.Canvas

abstract class GameObject {
    abstract fun draw(canvas : Canvas)
    abstract fun update()
}