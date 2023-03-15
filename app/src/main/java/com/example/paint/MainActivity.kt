package com.example.paint

import android.R
import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import com.example.paint.databinding.ActivityMainBinding
import java.util.concurrent.Executors
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var initY: Float = 0f
    private var initX: Float = 0f
    private lateinit var binding: ActivityMainBinding
    private var executor = Executors.newSingleThreadExecutor()
    private var paint: Paint? = null
    private var drawing : Boolean = false
    private var canvas : Canvas? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        paint = Paint()
        paint!!.style = Paint.Style.STROKE
        paint!!.strokeWidth = 3f
        paint!!.color = Color.WHITE
        val runnable = Runnable {
            while(true) {
            if (drawing) {
                canvas = binding.surfaceView2.holder.lockCanvas()
            canvas!!.drawCircle(initX, initY,20f, paint!!)
            binding.surfaceView2.holder.unlockCanvasAndPost(canvas)}}
        }
        binding.surfaceView2.setOnTouchListener { _: View, motionEvent: MotionEvent ->

            when (motionEvent.action) {
                MotionEvent.ACTION_MOVE -> {
                    Log.d("Main","MOVE PRESSED")
                    initX = motionEvent.x
                    initY = motionEvent.y



                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Main","DOWN PRESSED")
                    initX = motionEvent.x
                    initY = motionEvent.y

                    drawing = true
                }
                MotionEvent.ACTION_UP -> {
                    Log.d("Main","UP PRESSED")
                    drawing = false
                }
            }
            super.onTouchEvent(motionEvent)
        }

        binding.surfaceView2.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceCreated(p0: SurfaceHolder) {
                executor.submit(runnable)
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                executor.shutdown()
            }
        })
    }
}