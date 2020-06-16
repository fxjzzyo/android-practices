package com.example.android_practice.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.android_practice.R;

/**
 * Created by fanlulin
 * on date 2020/6/15 0015
 *
 * 沿着贝塞尔曲线运动
 */
public class BeisaierWaveView2 extends View {
    private Paint paint;
    private Path path;

    private int waveWidth = 800;
    // 动画进度
    private float fraction;
    private int dy;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private int width;
    private int height;
    // 某一点的位置坐标
    private float[] pos;
    // 某一点的tan值
    private float[] tans;

    public BeisaierWaveView2(Context context) {
        super(context);
        init();
    }


    public BeisaierWaveView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);

        path = new Path();



        mMatrix = new Matrix();
        pos = new float[2];
        tans = new float[2];

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_4);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        initPathData();
    }

    private void initPathData() {
        path.reset();
        int y = 500;
        int halfWaveWidth = waveWidth/2;
        path.moveTo(-waveWidth,y);

        for (int i = 0;i<getWidth()+waveWidth;i+=waveWidth){
            path.rQuadTo(halfWaveWidth/2,-150,halfWaveWidth,0);
            path.rQuadTo(halfWaveWidth/2,150,halfWaveWidth,0);
        }

        path.lineTo(getWidth(), getHeight());
        path.lineTo(0,getHeight());
        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(path,paint);

        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,false);

        // 方法一 使用 getPosTan
//        pathMeasure.getPosTan(
//                pathMeasure.getLength()*fraction,
//                pos,
//                tans
//        );
//        Matrix matrix = new Matrix();
//        float degrees = (float) (Math.atan2(tans[1], tans[0]) * 180 / Math.PI);
//        matrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
//        matrix.postTranslate(pos[0]-mBitmap.getWidth()/2,pos[1]-mBitmap.getHeight());
//        canvas.drawBitmap(mBitmap,matrix,paint);

        // 方法二 使用 getMatrix
        pathMeasure.getMatrix(
                pathMeasure.getLength()*fraction,
                mMatrix,
                PathMeasure.TANGENT_MATRIX_FLAG|PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preRotate(180);
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight());
        canvas.drawBitmap(mBitmap,mMatrix,paint);
    }

    public void startAnimaton() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1f);
        valueAnimator.setDuration(8000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }


}
