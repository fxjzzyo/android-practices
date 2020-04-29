package com.example.android_practice.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.example.android_practice.R;

public class Transition2Activity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置允许使用转场
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition2);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 滑动
            Slide slide = null;
            slide = new Slide(Gravity.BOTTOM);
            slide.setDuration(500);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
            getWindow().setReenterTransition(slide);
            getWindow().setReturnTransition(slide);
            //内容变换，不包括底部导航栏和状态栏
            slide.excludeTarget(android.R.id.navigationBarBackground, true);
            slide.excludeTarget(android.R.id.statusBarBackground, true);
            slide.excludeTarget(R.id.toolbar, true);

//            // 展开
//            Explode explode = new Explode();
//            explode.setDuration(500);
//            getWindow().setExitTransition(explode);
//            getWindow().setEnterTransition(explode);

//            // 渐变
//            Fade fade = new Fade();
//            fade.setDuration(500);
//            getWindow().setExitTransition(fade);
//            getWindow().setEnterTransition(fade);
        }

    }


    /**
     * 结束返回原来的activity
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void back(View view) {
        // 返回的转场动画 系统在onBackPressed里已经自动实现了
        // 关键方法: finishAfterTransition
//        onBackPressed();
        finishAfterTransition();
    }
}
