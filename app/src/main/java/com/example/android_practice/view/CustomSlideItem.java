package com.example.android_practice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

/**
 * Created by fanlulin
 * on date 2020/5/6 0006
 */
public class CustomSlideItem extends LinearLayout {
    private float startX;
    private float startY;
    private float dx;
    private float dy;
    private View leftView;
    private View rightView;

    private Scroller mScroller;

    public CustomSlideItem(Context context) {
        super(context);
    }

    public CustomSlideItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        mScroller = new Scroller(getContext(), null, true);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        leftView = getChildAt(0);
        rightView = getChildAt(1);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                Log.i("fxj","--down----dx------:"+startX);
                super.dispatchTouchEvent(ev);
                return true;
            case MotionEvent.ACTION_MOVE:
                dx = ev.getX() - startX;
                dy = ev.getY() - startY;
                Log.i("fxj","--move----dx------:"+dx);
                if (Math.abs(dx) - Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    //滑动的距离不能大于rightWidth
                    if(getScrollX() + (-dx)>rightView.getWidth()||getScrollX() + (-dx)<0){
                        return true;
                    }
                    this.scrollBy((int) -dx,0);
                    startX = ev.getX();
                    startY = ev.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("fxj","--up----scorllx------:"+getScrollX());
                Log.i("fxj","--up----rightView.getWidth------:"+rightView.getWidth());
                int offset = getScrollX() / (float)rightView.getWidth() > 0.5f?rightView.getWidth()-getScrollX():-getScrollX();
                Log.i("fxj","--up----offset------:"+offset);
                mScroller.startScroll(getScrollX(),getScrollY(),offset,0);
                invalidate();
                startX = 0;
                startY = 0;
                dx = 0;
                dy = 0;
                break;
            default:
                break;

        }

        return super.dispatchTouchEvent(ev);
    }


    /**
     * 在开启滑动的情况下，滑动的过程此方法会被不断调用
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }

    }



}
