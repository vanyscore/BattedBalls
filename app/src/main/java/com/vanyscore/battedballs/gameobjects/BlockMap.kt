package com.vanyscore.battedballs.gameobjects

import android.graphics.Canvas
import com.vanyscore.battedballs.Drawable
import com.vanyscore.battedballs.GameApp

class BlockMap(borderSize : Float,
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
                            blockWidth, blockHeight))
                }
            }
        }
    }

    override fun draw(canvas: Canvas) {
        blockMap.forEach {
            it.draw(canvas)
        }
    }

    fun getBlocks() : MutableList<Block> {
        return blockMap
    }
}