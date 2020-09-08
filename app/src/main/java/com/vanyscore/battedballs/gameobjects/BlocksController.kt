package com.vanyscore.battedballs.gameobjects

import android.graphics.Canvas
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.GameApp
import com.vanyscore.battedballs.Intersectable
import com.vanyscore.battedballs.intersects
import kotlin.random.Random

class BlocksController(borderSize : Float,
                       map : Array<Array<Int>>) : Drawable {

    private val blockMap = mutableListOf<Block>()

    init {
        val blockWidth = (GameApp.screenWidth - borderSize * 6f) / map[0].size
        val blockHeight = GameApp.screenHeight * 0.05f

        for (i in map.indices) {
            for (k in map[i].indices) {
                if (map[i][k] == 1) {
                    blockMap
                        .add(Block(borderSize * 3 + (k * blockWidth),
                            borderSize * 3 + (i * blockHeight),
                            blockWidth, blockHeight, Random.nextInt(4)))
                }
            }
        }
    }

    override fun draw(canvas: Canvas) {
        blockMap.forEach {
            it.draw(canvas)
        }
    }

    fun handleCollision(obj: Intersectable, onFinishGame: ((isWon: Boolean) -> Unit)): Block? {
        val iterator = blockMap.iterator()

        while (iterator.hasNext()) {
            val block = iterator.next()
            val blockRect = block.getRect()

            if (blockRect.intersects(obj.getRect())) {
                val isDestroyed = block.tryToDestroy()

                if (isDestroyed) {
                    iterator.remove()

                    if (blockMap.size == 0)
                        onFinishGame(true)
                }

                return block
            }
        }

        return null
    }
}