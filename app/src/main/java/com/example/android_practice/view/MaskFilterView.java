package com.example.android_practice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.android_practice.R;

/**
 * Created by fanlulin
 * on date 2020/6/6 0006
 *
 * 滤镜遮罩View
 *
 */
public class MaskFilterView extends View {
    public MaskFilterView(Context context) {
        super(context);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // =======================遮罩滤镜处理=======================
//        // 关闭硬件加速
//        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
//
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.RED);
//        RectF rectF = new RectF(0, 0, 200, 200);


        // 模糊遮罩滤镜效果
//        BlurMaskFilter.Blur.INNER
//        BlurMaskFilter.Blur.NORMAL
//        BlurMaskFilter.Blur.OUTER
//        BlurMaskFilter.Blur.SOLID
//        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
//        canvas.drawRect(rectF,paint);


        // 浮雕遮罩效果
        /**
         *  direction  array of 3 scalars [x, y, z] specifying the direction of the light source
         *  ambient    0...1 amount of ambient light指定周边背景光源
         *  specular   coefficient for specular highlights (e.g. 8)镜面反射系数
         * blurRadius amount to blur before applying lighting (e.g. 3)模糊半径
         */
//        paint.setMaskFilter(new EmbossMaskFilter(new float[]{50, 50, 50}, 0.2f, 8, 50));
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img3);
//        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 600, 800, false);
//        canvas.drawBitmap(scaledBitmap,100,300,paint);
//        bitmap.recycle();
//        scaledBitmap.recycle();

        // ======================= 颜色的RGB滤镜处理=======================

        // 关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.argb(255,200,100,100));
        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawRect(0,0,40,40,paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jay2);

        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400,100+400*bitmap.getHeight()/bitmap.getWidth()),
                paint);
        canvas.translate(400,0);

        // 颜色矩阵 只保留蓝色通道
        ColorMatrix matrix = new ColorMatrix(new float[]{
           0,0,0,0,0,
           0,0,0,0,0,
           0,0,1,0,0,
           0,0,0,1,0,
        });

        // 设置颜色过滤器
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawRect(0,0,40,40,paint);
        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400,100+400*bitmap.getHeight()/bitmap.getWidth()),
                paint);
        canvas.translate(-400,400);

        // 反向效果滤镜
        ColorMatrix matrix2 = new ColorMatrix(new float[]{
                -1,0,0,0,255,
                0,-1,0,0,255,
                0,0,-1,0,255,
                0,0,0,1,0,
        });
        // 设置颜色过滤器
        paint.setColorFilter(new ColorMatrixColorFilter(matrix2));
        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400,100+400*bitmap.getHeight()/bitmap.getWidth()),
                paint);
        canvas.translate(400,0);

        // 色彩增强效果滤镜
        ColorMatrix matrix3 = new ColorMatrix(new float[]{
                1.2f,0,0,0,0,
                0,1.2f,0,0,0,
                0,0,1.2f,0,0,
                0,0,0,1,0,
        });
        // 设置颜色过滤器
        paint.setColorFilter(new ColorMatrixColorFilter(matrix3));
        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400,100+400*bitmap.getHeight()/bitmap.getWidth()),
                paint);
        canvas.translate(-400,400);
        // 灰度效果滤镜
        ColorMatrix matrix4 = new ColorMatrix(new float[]{
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0,0,0,1,0,
        });
        // 设置颜色过滤器
        paint.setColorFilter(new ColorMatrixColorFilter(matrix4));
        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400*bitmap.getWidth()/bitmap.getHeight(),500),
                paint);
        canvas.translate(400,0);
        // 红绿色彩交换效果滤镜
        ColorMatrix matrix5 = new ColorMatrix(new float[]{
                0,1f,0,0,0,
                1f,0,0,0,0,
                0,0,1f,0,0,
                0,0,0,1,0,
        });
        // 设置颜色过滤器
        paint.setColorFilter(new ColorMatrixColorFilter(matrix5));
        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400*bitmap.getWidth()/bitmap.getHeight(),500),
                paint);

        canvas.translate(-400,400);
        // 复古效果滤镜
        ColorMatrix matrix6 = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        });
        // 设置颜色过滤器
        paint.setColorFilter(new ColorMatrixColorFilter(matrix6));
        canvas.drawBitmap(bitmap,null,
                new RectF(0,100,400*bitmap.getWidth()/bitmap.getHeight(),500),
                paint);
    }
}
