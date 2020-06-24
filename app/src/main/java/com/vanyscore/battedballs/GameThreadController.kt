package com.vanyscore.battedballs

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View

class GameThreadController(holder : SurfaceHolder) : SurfaceHolder.Callback,
    View.OnTouchListener {

    private lateinit var gameThread : Thread
    private lateinit var gameRunnable : GameRunnable

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        // do something
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        holder.removeCallback(this)

        gameThread.interrupt()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameThread = Thread(GameRunnable(holder).also {
            this.gameRunnable = it
        }).also {
            it.start()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?) : Boolean {
        if (event != null)
            this.gameRunnable.onTouch(event)

        return true
    }
}