package com.example.android_practice.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.android_practice.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fanlulin
 * on date 2020/5/6 0006
 */
public class CustomCircle extends View {
    private float radius;
    private float circleY;
    private Paint paint;
    private int color;
    private float circleX;
    private int swapAngle;

    public CustomCircle(Context context) {
        super(context);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCircle);
        color = typedArray.getColor(R.styleable.CustomCircle_circle_color, Color.GREEN);
        circleX = typedArray.getDimension(R.styleable.CustomCircle_circle_x, 100);
        circleY = typedArray.getDimension(R.styleable.CustomCircle_circle_y, 100);
        radius = typedArray.getDimension(R.styleable.CustomCircle_circle_radius, 100);
        typedArray.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                swapAngle += 1;
                if (swapAngle > 360) {
                    swapAngle = 0;
                }
                postInvalidate();
            }
        }, 1000, 20);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画圆
        paint.setColor(color);
        paint.setStrokeWidth(10f);
        canvas.drawCircle(circleX, circleY, radius, paint);

        // 进度条走动
        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(circleX - radius, circleY - radius, circleX + radius,
                circleY + radius);
        canvas.drawArc(rectF, -90, swapAngle, false, paint);

        // 绘制文字
        int progress = (int) (swapAngle / 360f * 100);
        paint.setStrokeWidth(0);
        //百分数颜色
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText(progress + "%", circleX-15, circleY, paint);

    }
}
