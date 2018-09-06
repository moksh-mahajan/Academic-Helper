package com.example.moksh.academichelplerdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import java.util.Random

class LetterImageView(context: Context, attrs: AttributeSet) : android.support.v7.widget.AppCompatImageView(context, attrs) {

    var letter: Char = ' '
        set(letter) {
            field = letter
            invalidate()
        }
    private var mTextPaint: Paint? = null
    private var mBackgroundPaint: Paint? = null
    var textColor = Color.WHITE
        set(textColor) {
            field = textColor
            invalidate()
        }
    var isOval: Boolean = false

    private// Set a default padding to 8dp
    val textPadding: Float
        get() = 8 * resources.displayMetrics.density

    init {
        init()
    }

    private fun init() {
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint!!.color = textColor
        mBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBackgroundPaint!!.style = Paint.Style.FILL
        mBackgroundPaint!!.color = randomColor()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (drawable == null) {
            // Set a text font size based on the height of the view
            mTextPaint!!.textSize = canvas.height - textPadding * 2
            if (isOval) {
                canvas.drawCircle(canvas.width / 2f, canvas.height / 2f, Math.min(canvas.width, canvas.height) / 2f,
                        mBackgroundPaint!!)
            } else {
                canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), mBackgroundPaint!!)
            }
            // Measure a text
            val textBounds = Rect()
            mTextPaint!!.getTextBounds(letter.toString(), 0, 1, textBounds)
            val textWidth = mTextPaint!!.measureText(letter.toString())
            val textHeight = textBounds.height().toFloat()
            // Draw the text
            canvas.drawText(letter.toString(), canvas.width / 2f - textWidth / 2f,
                    canvas.height / 2f + textHeight / 2f, mTextPaint!!)
        }
    }

    private fun randomColor(): Int {
        val random = Random()
        val colorsArr = resources.getStringArray(R.array.colors)
        return Color.parseColor(colorsArr[random.nextInt(colorsArr.size)])
    }


}