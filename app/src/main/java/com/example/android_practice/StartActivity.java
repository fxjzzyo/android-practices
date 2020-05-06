package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_practice.activity.CustomCircleActivity;
import com.example.android_practice.activity.CustomSlidMenuActivity;
import com.example.android_practice.activity.EventDispatchActivity;
import com.example.android_practice.activity.PaletteActivity;
import com.example.android_practice.activity.ParallaxSplashActivity;
import com.example.android_practice.activity.PropertyAnimationSampleActivity;
import com.example.android_practice.activity.RippleBtnActivity;
import com.example.android_practice.activity.Transition1Activity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    /**
     * 跳转到fragment练习
     * @param view
     */
    public void toFragmentPractice(View view) {
        Intent intent = new Intent(this, FragmentPracticeActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到Palette练习
     * @param view
     */
    public void toPalettePractice(View view) {
        Intent intent = new Intent(this, PaletteActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到水波按钮练习
     * @param view
     */
    public void toRippleBtnPractice(View view) {
        Intent intent = new Intent(this, RippleBtnActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到属性动画案例练习
     * @param view
     */
    public void toPropertyAnimationSamplePractice(View view) {
        Intent intent = new Intent(this, PropertyAnimationSampleActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到activity转场练习
     * @param view
     */
    public void toTransitionActivityPractice(View view) {
        Intent intent = new Intent(this, Transition1Activity.class);
        startActivity(intent);
    }


    /**
     * 跳转到View事件分发activity练习
     * @param view
     */
    public void toEventDispatchActivityPractice(View view) {
        Intent intent = new Intent(this, EventDispatchActivity.class);
        startActivity(intent);
    }


    /**
     *跳转到自定义圆环进度条练习
     * @param view
     */
    public void toCustomCirclePractice(View view) {
        Intent intent = new Intent(this, CustomCircleActivity.class);

        startActivity(intent);
    }
    /**
     * 跳转到ParallaxSplashActivity视差动画框架练习
     * @param view
     */
    public void toParallaxSplashActivity(View view) {
        Intent intent = new Intent(this, ParallaxSplashActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到自定义侧滑菜单练习
     * @param view
     */
    public void toCustomSlidMenuActivity(View view) {
        Intent intent = new Intent(this, CustomSlidMenuActivity.class);
        startActivity(intent);
    }
}
