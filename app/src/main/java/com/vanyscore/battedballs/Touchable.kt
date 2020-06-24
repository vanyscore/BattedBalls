package com.vanyscore.battedballs

import android.view.MotionEvent

interface Touchable {
    fun onTouch(event : MotionEvent)
}