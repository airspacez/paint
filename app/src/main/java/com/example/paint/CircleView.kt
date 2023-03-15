package com.example.paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import java.util.*


class CircleView : View {
    private var paint: Paint? = null
    private var radius = 0f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }


    private fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)

//        paint!!.isAntiAlias = true;
//        paint!!.strokeWidth = 2F;
//        paint!!.color = -7829368;
//        paint!!.style = Paint.Style.STROKE;
        paint!!.color = Color.RED
        radius = 50f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        canvas.drawCircle(centerX, centerY, radius, paint!!)
    }

    fun setRadius(radius: Float) {
        this.radius = radius
        invalidate()
    }



}