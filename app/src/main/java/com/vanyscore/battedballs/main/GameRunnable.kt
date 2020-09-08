package com.vanyscore.battedballs.main

import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.vanyscore.battedballs.*
import com.vanyscore.battedballs.controllers.BallController
import com.vanyscore.battedballs.controllers.PlatformController
import com.vanyscore.battedballs.gameobjects.*

class GameRunnable(private val mainHolder : SurfaceHolder,
                   finishListener : (isWon: Boolean) -> Unit) : Runnable,
    Touchable {

    private val drawables : MutableList<Drawable> = mutableListOf()
    private val updatable : MutableList<Updatable> = mutableListOf()
    private val touchables : MutableList<Touchable> = mutableListOf()

    init {
        val fpsMonitor = FpsMonitor()
        val ball = Ball(
            GameApp.screenWidth / 2f,
            GameApp.screenHeight / 2f,
            20f)

        val borderSize = GameApp.parseIntoPx(30f)
        val w = GameApp.screenWidth
        val h = GameApp.screenHeight

        val borders = listOf(
            Border(RectF(0f, 0f, w, borderSize)),
            Border(RectF(0f, 0f, borderSize, h)),
            Border(RectF(w - borderSize, 0f, w, h))
        )

        val array = Array(5) { Array(8) { 0 } }
        array[0] = arrayOf(1, 1, 1, 1, 1, 1, 1)
        array[1] = arrayOf(1, 1, 1, 1, 1, 1, 1)
        array[2] = arrayOf(0, 1, 1, 1, 1, 1, 0)
        array[3] = arrayOf(0, 0, 1, 1, 1, 0, 0)
        array[4] = arrayOf(0, 0, 1, 1, 1, 0, 0)
        val blockMap = BlocksController(borderSize, array)

        val platform = Platform().apply {
            val width = (GameApp.screenWidth - borderSize * 2) / 4f
            val height = width / 4f
            val left = GameApp.screenWidth / 2f - width / 2f
            val right = left + width
            val top = GameApp.screenHeight - borderSize * 3f
            val bottom = top + height

            initRect(RectF(left, top, right, bottom))
        }

        drawables.add(fpsMonitor)
        drawables.add(ball)
        drawables.addAll(borders)
        drawables.add(blockMap)
        drawables.add(platform)

        val intersectList = mutableListOf<Intersectable>().apply {
            addAll(borders)
            add(platform)
        }

        updatable.add(fpsMonitor)
        updatable.add(BallController(ball, intersectList,
            blockMap, finishListener))

        val platformController = PlatformController(platform, borders[1], borders[2])

        touchables.add(platformController)
    }

    override fun run() {
        while (!Thread.interrupted()) {
            val canvas = mainHolder.lockCanvas() ?: continue

            canvas.drawColor(Color.BLACK)

            updatable.forEach {
                it.update()
            }

            drawables.forEach {
                it.draw(canvas)
            }

            mainHolder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouch(event: MotionEvent) {
        touchables.forEach {
            it.onTouch(event)
        }
    }
}