package com.example.android_practice.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_practice.R;

public class PaletteActivity extends AppCompatActivity {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tvImageDesc;

    private ImageView ivImage;

    private int DEFAUT_COLOR = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        ivImage = findViewById(R.id.iv_image);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);
        tvImageDesc = findViewById(R.id.tv_image_desc);

        BitmapDrawable ivImageDrawable = (BitmapDrawable) ivImage.getDrawable();
        Bitmap ivImageDrawableBitmap = ivImageDrawable.getBitmap();

        Palette.from(ivImageDrawableBitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {

                int darkMutedColor = palette.getDarkMutedColor(DEFAUT_COLOR);
                tv1.setBackgroundColor(darkMutedColor);
                tv1.setText("darkMutedColor");

                int mutedColor = palette.getMutedColor(DEFAUT_COLOR);
                tv2.setBackgroundColor(mutedColor);
                tv2.setText("mutedColor");


                int lightMutedColor = palette.getLightMutedColor(DEFAUT_COLOR);
                tv3.setBackgroundColor(lightMutedColor);
                tv3.setText("lightMutedColor");

                int darkVibrantColor = palette.getDarkVibrantColor(DEFAUT_COLOR);
                tv4.setBackgroundColor(darkVibrantColor);
                tv4.setText("darkVibrantColor");

                int vibrantColor = palette.getVibrantColor(DEFAUT_COLOR);
                tv5.setBackgroundColor(vibrantColor);
                tv5.setText("vibrantColor");

                int lightVibrantColor = palette.getLightVibrantColor(DEFAUT_COLOR);
                tv6.setBackgroundColor(lightVibrantColor);
                tv6.setText("lightVibrantColor");

                int dominantColor = palette.getDominantColor(DEFAUT_COLOR);
                tv7.setBackgroundColor(dominantColor);
                tv7.setText("dominantColor");

                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

                int bodyTextColor = vibrantSwatch.getBodyTextColor();
                tvImageDesc.setBackgroundColor(bodyTextColor);
                int titleTextColor = vibrantSwatch.getTitleTextColor();
                tvImageDesc.setTextColor(titleTextColor);
                tvImageDesc.setText("这是图片");



            }
        });






    }
}
