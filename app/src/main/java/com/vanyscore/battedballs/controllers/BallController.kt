package com.vanyscore.battedballs.controllers

import android.graphics.PointF
import android.util.Log
import com.vanyscore.battedballs.Batted
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.Updatable
import com.vanyscore.battedballs.gameobjects.Ball
import com.vanyscore.battedballs.gameobjects.Borders
import kotlin.math.acos
import kotlin.math.sqrt

class BallController(private val ball : Ball,
                     private val borders : Borders) : Updatable {

    private val diff = 1
    private var lastTime = System.currentTimeMillis()

    private var vx : Float = 10f
    private var vy : Float = 10f

    private var prevPoint = PointF(ball.getRect().centerX(), ball.getRect().centerY())

    override fun update() {
        if (System.currentTimeMillis() - lastTime >= diff) {
            ball.x += vx
            ball.y += vy

            lastTime = System.currentTimeMillis()

            checkCollision()
        }
    }

    private fun checkCollision() {
        borders.getBorders().forEach { border ->
            if (ball.getRect().intersects(border.left, border.top,
                    border.right, border.bottom))
                handleCollision(borders.getDirection(ball.getRect()))
        }
    }

    private fun handleCollision(direct : Batted.Direction) {
        val currPoint = PointF(ball.getRect().centerX(), ball.getRect().centerY())

        Log.d(GameApp.DEBUG_KEY, direct.toString())

        when (direct) {
            Batted.Direction.RIGHT -> vx *= -1
            Batted.Direction.BOTTOM -> vy *= -1
            Batted.Direction.LEFT -> vx *= -1
            Batted.Direction.TOP -> vy *= -1
        }

        prevPoint = currPoint
    }
}