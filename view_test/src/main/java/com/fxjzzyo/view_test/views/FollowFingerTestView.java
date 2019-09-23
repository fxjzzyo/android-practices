package com.fxjzzyo.view_test.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.nineoldandroids.view.ViewHelper;

/**
 * @author fanlulin
 * @since 2019-09-03
 */
public class FollowFingerTestView extends View {

    private int mLastX;
    private int mLastY;

    public FollowFingerTestView(Context context) {
        super(context);
    }

    public FollowFingerTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FollowFingerTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
                case MotionEvent.ACTION_MOVE:
                    int delaX = x - mLastX;
                    int delaY = y - mLastY;
                    int translationX = (int) (ViewHelper.getTranslationX(this)+delaX);
                    int translationY = (int) (ViewHelper.getTranslationY(this)+delaY);
                    ViewHelper.setTranslationX(this,translationX);
                    ViewHelper.setTranslationY(this,translationY);
                    break;
            case MotionEvent.ACTION_UP:

                break;
                default:break;
        }

        mLastX = x;
        mLastY = y;

        return true;
    }
}
