package com.vanyscore.battedballs.controllers

import android.graphics.PointF
import android.graphics.RectF
import android.util.Log
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.Intersectable
import com.vanyscore.battedballs.Updatable
import com.vanyscore.battedballs.gameobjects.Ball
import com.vanyscore.battedballs.gameobjects.BlocksController
import com.vanyscore.battedballs.intersects

class BallController(private val ball : Ball,
                     private val barriers : List<Intersectable>,
                     private val blocksController : BlocksController,
                     private val finishListener : (isWon: Boolean) -> Unit) : Updatable {

    private val velocity = 15f

    private var vx : Float = velocity
    private var vy : Float = velocity

    private var prevPoint = PointF(ball.getRect().centerX(), ball.getRect().centerY())

    override fun update() {
        ball.x += vx
        ball.y += vy

        if (ball.y >= GameApp.screenHeight)
            finishListener(false)

        checkCollision()
    }

    private fun checkCollision() {
        barriers.map {
            it.getRect()
        }.forEach { rect ->
            if (ball.getRect().intersects(rect))
                handleCollision(rect)
        }

        blocksController.handleCollision(ball, finishListener)?.also {
            handleCollision(it.getRect())
        }
    }

    private fun handleCollision(rect : RectF) {
        ball.x -= vx * 1.1f
        ball.y -= vy * 1.1f

        val ballRect = ball.getRect()
        val currPoint = PointF(ballRect.left, ballRect.centerY())

        when {
            currPoint.x < prevPoint.x && currPoint.y < prevPoint.y -> {
                if (rect.right < currPoint.x)
                    vx = velocity
                else
                    vy = velocity
            }
            currPoint.x > prevPoint.x && currPoint.y < prevPoint.y -> {
                if (rect.left > currPoint.x + ball.radius)
                    vx = velocity * -1
                else
                    vy = velocity
            }
            currPoint.x > prevPoint.x && currPoint.y > prevPoint.y -> {
                if (rect.left > currPoint.x + ball.radius)
                    vx = velocity * -1
                else
                    vy = velocity * -1
            }
            currPoint.x < prevPoint.x && currPoint.y > prevPoint.y -> {
                if (rect.right < currPoint.x)
                    vx = velocity
                else
                    vy = velocity * -1
            }
        }

        prevPoint = currPoint
    }
}