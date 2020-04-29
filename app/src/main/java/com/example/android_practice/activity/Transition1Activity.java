package com.example.android_practice.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android_practice.R;

/**
 * 转场效果练习
 */
public class Transition1Activity extends AppCompatActivity {

    private Button btnJump;
    private ImageView mImageView;
    private Toolbar mToolbar;

    @Override
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onCreate(Bundle savedInstanceState) {
        // 设置允许使用转场
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // 新形式的转场动画 ，有bug，由A跳到B可以，但是返回A后，A出现空白页。
            //
            // 滑动
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.setDuration(500);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
//
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
            // todo 有bug，由A跳到B可以，但是返回A后，A出现空白页。

        }


        btnJump = findViewById(R.id.btn_jump);
        mImageView = findViewById(R.id.iv_image);
    }

    /**
     * 跳转
     *
     * @param view
     */
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void jumpTo2Activity(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // 新形式的转场动画
            // 滑动
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.setDuration(500);
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
            getWindow().setReenterTransition(slide);
            getWindow().setReturnTransition(slide);
            // 多个元素转场
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    Pair.create((View) btnJump, "transBtn"),
                    Pair.create((View) mImageView, "transImage"));
            // 单个元素转场
            //      ActivityOptionsCompat optionsCompat2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            //      mImageView, "transImage");
//            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(this, Transition2Activity.class);
            startActivity(intent, optionsCompat.toBundle());
            // 传统形式的转场动画
//            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }

    }


    /**
     * 跳转
     *
     * @param view
     */
    public void jumpTo2Activity2(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // acitivity转场
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(this, Transition2Activity.class);
            startActivity(intent, optionsCompat.toBundle());
        }

    }
}
