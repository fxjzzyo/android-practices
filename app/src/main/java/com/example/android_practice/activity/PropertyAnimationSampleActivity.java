package com.example.android_practice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android_practice.R;

public class PropertyAnimationSampleActivity extends AppCompatActivity {

    private LinearLayout llView1;
    private ImageView ivView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_sample);
        llView1 = findViewById(R.id.ll_view1);
        ivView2 = findViewById(R.id.iv_view2);
    }

    /**
     * 显示下方窗口
     * @param view
     */
    public void showBottom(View view) {
        // 上方窗口动画 1. 旋转 2.透明度 3.缩放 4.平移
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(llView1,"rotationX",0f,25f);
        rotateAnimator.setDuration(300);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(llView1,"alpha",1f,0.5f);
        alphaAnimator.setDuration(200);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(llView1,"scaleX",1f,0.8f);
        scaleXAnimator.setDuration(300);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(llView1,"scaleY",1f,0.8f);
        scaleYAnimator.setDuration(300);

        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(llView1,"translationY",0f,-0.1f*llView1.getHeight());
        translateYAnimator.setDuration(300);

        ObjectAnimator rotateResumeAnimator = ObjectAnimator.ofFloat(llView1,"rotationX",25f,0f);
        rotateResumeAnimator.setStartDelay(200);
        rotateAnimator.setDuration(200);
        // 下方窗口动画
        ObjectAnimator secondTranslateYAnimator = ObjectAnimator.ofFloat(ivView2,"translationY",ivView2.getHeight(),0f);
        secondTranslateYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivView2.setVisibility(View.VISIBLE);
                llView1.setClickable(false);
            }
        });
        secondTranslateYAnimator.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateAnimator,alphaAnimator,scaleXAnimator,scaleYAnimator,rotateResumeAnimator,translateYAnimator,secondTranslateYAnimator);
        animatorSet.start();





    }


    /**
     * 隐藏下方窗口
     * @param view
     */
    public void hideBottom(View view) {
        // 动画 1. 旋转 2.透明度 3.缩放 4.平移
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(llView1,"rotationX",0f,25f);
        rotateAnimator.setDuration(300);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(llView1,"alpha",0.5f,1f);
        alphaAnimator.setDuration(200);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(llView1,"scaleX",0.8f,1f);
        scaleXAnimator.setDuration(300);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(llView1,"scaleY",0.8f,1f);
        scaleYAnimator.setDuration(300);

        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(llView1,"translationY",-0.1f*view.getHeight(),0f);
        translateYAnimator.setDuration(300);

        ObjectAnimator rotateResumeAnimator = ObjectAnimator.ofFloat(llView1,"rotationX",25f,0f);
        rotateResumeAnimator.setStartDelay(200);
        rotateAnimator.setDuration(200);

        // 下方窗口动画
        ObjectAnimator secondTranslateYAnimator = ObjectAnimator.ofFloat(ivView2,"translationY",0f,ivView2.getHeight());
        secondTranslateYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                ivView2.setVisibility(View.INVISIBLE);
                llView1.setClickable(true);
            }
        });
        secondTranslateYAnimator.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotateAnimator,alphaAnimator,scaleXAnimator,scaleYAnimator,rotateResumeAnimator,translateYAnimator,secondTranslateYAnimator);

        animatorSet.start();


    }
}
