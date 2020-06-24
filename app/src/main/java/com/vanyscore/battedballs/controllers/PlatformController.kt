package com.vanyscore.battedballs.controllers

import android.util.Log
import android.view.MotionEvent
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.Touchable
import com.vanyscore.battedballs.gameobjects.Border
import com.vanyscore.battedballs.gameobjects.Platform

class PlatformController(private val platform : Platform,
                         private val leftBorder: Border,
                         private val rightBorder: Border) : Touchable {

    private val plHalfWidth = platform.getRect().width() / 2

    override fun onTouch(event: MotionEvent) {
        if (event.action == MotionEvent.ACTION_DOWN
            || event.action == MotionEvent.ACTION_MOVE) {
            val platformRect = platform.getRect()

            val newLeft = event.x - plHalfWidth
            val newRight = newLeft + platformRect.width()

            if (newLeft >= leftBorder.getRect().right
                && newRight <= rightBorder.getRect().left) {
                platformRect.apply {
                    left = newLeft
                    right = newRight
                }
            }
        }
    }
}