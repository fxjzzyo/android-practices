package com.example.android_practice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import com.example.android_practice.R;

public class RippleBtnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_btn);
    }


    /**
     * 按钮的揭露显示效果
     * @param view
     */
    public void jieLou(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(view,
                    view.getWidth() / 2,
                    view.getHeight() / 2,
                    0f,
                    view.getWidth() / 2);
            animator.setDuration(500);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.start();
        }


    }
}
