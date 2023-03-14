
import android.view.animation.Animation
import android.view.animation.Transformation


class CircleAnimation(circleView: CircleView, startRadius: Float, endRadius: Float) :
    Animation() {
    private val circleView: CircleView
    private val startRadius: Float
    private val endRadius: Float

    init {
        this.circleView = circleView
        this.startRadius = startRadius
        this.endRadius = endRadius
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val radius = startRadius + (endRadius - startRadius) * interpolatedTime
        circleView.setRadius(radius)
    }
}