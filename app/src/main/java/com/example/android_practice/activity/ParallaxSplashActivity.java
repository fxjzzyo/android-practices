package com.example.android_practice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.android_practice.ParallaxContainer;
import com.example.android_practice.R;

public class ParallaxSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_splash);

        ParallaxContainer parallaxContainer = findViewById(R.id.parallax_container);
        parallaxContainer.setup(new int[]{
            R.layout.view_intro_1,
            R.layout.view_intro_2,
            R.layout.view_intro_3,
            R.layout.view_intro_4,
            R.layout.view_intro_5,
            R.layout.view_login
        });
        // 设置动画
        ImageView ivMan = findViewById(R.id.iv_man);
        ivMan.setBackgroundResource(R.drawable.man_run);
        parallaxContainer.setIv_man(ivMan);

    }
}
