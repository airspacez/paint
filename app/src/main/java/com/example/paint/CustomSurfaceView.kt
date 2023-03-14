package com.example.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class CustomSurfaceView(context : Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val drawingThread = SurfaceDrawingThread(holder)
    init{
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawingThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        try{
            drawingThread.join()
        } catch (e: InterruptedException) { }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }
}

class SurfaceDrawingThread(private val surfaceHolder: SurfaceHolder) : Thread() {

    private val face = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val eyes = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val insideEyes = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val bigNose = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val mouth = Paint().apply {
        color = Color.YELLOW
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    override fun run() {
        val canvas: Canvas? = surfaceHolder.lockCanvas()
        canvas?.apply {
            drawColor(Color.WHITE)
            drawCircle((width / 2).toFloat(), (height / 2  - 230F), 500F, face)
            drawRect(200F, 200F, 300F, 300F, eyes)
            drawRect(750F, 200F, 850F, 300F, eyes)
            drawRect(220F, 235F, 250F, 265F, insideEyes)
            drawRect(770F, 235F, 800F, 265F, insideEyes)
            drawRect(320F, 300F, 520F, 500F, bigNose)
            drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 200F, mouth)
            
        }
        surfaceHolder.unlockCanvasAndPost(canvas)
    }
}