package com.vanyscore.battedballs

import android.view.SurfaceHolder

class GameThreadController(holder : SurfaceHolder) : SurfaceHolder.Callback {

    private lateinit var gameThread : Thread

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // do something
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        holder?.removeCallback(this)
        gameThread.interrupt()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        gameThread = Thread(GameRunnable(holder!!))
        gameThread.start()
    }
}