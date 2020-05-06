package com.example.android_practice.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by fanlulin
 * on date 2020/5/6 0006
 */
public class CustomSlideMenu extends HorizontalScrollView {


    private int screenWidth;
    private boolean isOnce;
    private ViewGroup menu;
    private ViewGroup main;
    private int menuWidth;
    private int menuPaddingLeft = 100;
    private float downX;

    public CustomSlideMenu(Context context) {
        super(context);
    }

    public CustomSlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取屏幕宽度
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics matrix = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(matrix);
        screenWidth = matrix.widthPixels;
        Log.i("fxj","------screenWidth------"+screenWidth);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isOnce) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);

            menu = (ViewGroup) wrapper.getChildAt(0);
            main = (ViewGroup) wrapper.getChildAt(1);

            menuWidth = screenWidth - menuPaddingLeft;
            menu.getLayoutParams().width = menuWidth;
            main.getLayoutParams().width = screenWidth;

        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            Log.i("fxj","---changed---menuWidth------:"+menuWidth);
            this.scrollTo(menuWidth,0);
            isOnce = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();


                break;
            case MotionEvent.ACTION_UP:
                float dx = ev.getRawX() - downX;
                Log.i("fxj","------dx------:"+dx);
                Log.i("fxj","------menuWidth------:"+menuWidth);
                if (dx < screenWidth / 3) {
                    this.smoothScrollTo(menuWidth,0);
                }else{
                    this.smoothScrollTo(0,0);
                }
               return true;
                default:
                    break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        Log.i("fxj","------------:"+l+",t:"+t+", oldl:"+oldl+", oldt:"+oldt);

        // 动画效果
        // 动画因子 从左向右滑时，l:[menuWidth,0],factor:[1,0]
        float factor = (float) l/menuWidth;
        Log.i("fxj","------factor------:"+factor);
        // 平移
        ViewHelper.setTranslationX(menu,menuWidth*factor*0.6f);

        // 缩放
        float leftScale = (float) (1 - 0.4f * factor);
        Log.i("fxj","------leftScale------:"+leftScale);
        ViewHelper.setScaleX(menu, leftScale);
        ViewHelper.setScaleY(menu, leftScale);
        float rightScale = (float) (0.8 + 0.2f * factor);
        Log.i("fxj","------rightScale------:"+rightScale);
        ViewHelper.setScaleX(main, rightScale);
        ViewHelper.setScaleY(main, rightScale);

        // 透明度
        ViewHelper.setAlpha(menu, 1-factor);

    }
}
