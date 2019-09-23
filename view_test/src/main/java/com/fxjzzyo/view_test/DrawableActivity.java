package com.fxjzzyo.view_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.fxjzzyo.view_test.views.CustomDrawable;

public class DrawableActivity extends AppCompatActivity {

    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ImageView img1 = findViewById(R.id.iv_img1);
        Drawable clipDrawable = img1.getDrawable();
        clipDrawable.setLevel(1000);


        btn2 = findViewById(R.id.btn_2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btn2.setBackground(new CustomDrawable(Color.RED));
        }
    }
}
