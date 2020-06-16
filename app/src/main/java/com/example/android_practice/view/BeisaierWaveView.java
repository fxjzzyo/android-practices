package com.example.android_practice.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * Created by fanlulin
 * on date 2020/6/15 0015
 */
public class BeisaierWaveView extends View {
    private Paint paint;
    private Path path;

    private int waveWidth = 800;
    private int dx;
    private int dy;

    public BeisaierWaveView(Context context) {
        super(context);
        init();
    }


    public BeisaierWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.reset();
        int y = 500;
        if(dy<y-150){
            dy+=5;
        }
        int halfWaveWidth = waveWidth/2;

        path.moveTo(-waveWidth+dx,y-dy);

        for (int i = -waveWidth;i<getWidth()+waveWidth;i+=waveWidth){
            path.rQuadTo(halfWaveWidth/2,-150,halfWaveWidth,0);
            path.rQuadTo(halfWaveWidth/2,150,halfWaveWidth,0);
        }

        path.lineTo(getWidth(), getHeight());
        path.lineTo(0,getHeight());
        path.close();

        canvas.drawPath(path,paint);
    }

    public void startAnimaton() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,waveWidth);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }


}
