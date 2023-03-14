package com.example.paint

import CircleAnimation
import CircleView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circleView = findViewById<CircleView>(R.id.circle_view)
        circleView.setRadius(100f)

        val startRadius = 50f
        val endRadius = 200f
        val animation = CircleAnimation(circleView, startRadius, endRadius)
        animation.duration = 1000 // 1 second
        circleView.startAnimation(animation)
    }
}