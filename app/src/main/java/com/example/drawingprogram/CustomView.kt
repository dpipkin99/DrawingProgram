package com.example.drawingprogram

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import kotlin.random.Random

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var pathColor = Color.BLUE


    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path = Path()
    private var pathList= mutableListOf<Path>()
    private var paintList = mutableListOf<Int>()
    init{
        paint.color = Color.CYAN
        paint.strokeWidth = 20f
        paint.style = Paint.Style.STROKE;
    }

    fun touchStart(x: Float, y: Float){
        path = Path()
        pathList.add(path)
        path.moveTo(x, y)
        val color = randomizeColor()
        paintList.add(color)

    }
    fun touchMove(x: Float, y: Float){
        path.lineTo(x,y)
    }
    fun randomizeColor(): Int{
        return Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return when (event?.action)
        {
            MotionEvent.ACTION_DOWN -> {

                path = Path()



                touchStart(event.x, event.y)
                true
            }
            MotionEvent.ACTION_MOVE -> {

                touchMove(event.x, event.y)
                invalidate()
                true
            }
            MotionEvent.ACTION_UP -> {
                invalidate()
                true
            }
            else -> {
                super.onTouchEvent(event)
            }
        }
    }


    fun clearDrawing(){
        pathList.clear()
        paintList.clear()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in pathList.indices) {
            paint.color = paintList[i]
            canvas?.drawPath(pathList[i],paint )
        }
    }

}