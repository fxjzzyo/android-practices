package com.example.android_practice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_practice.R;
import com.example.android_practice.view.BeisaierWaveView;
import com.example.android_practice.view.BeisaierWaveView2;

/**
 * 贝塞尔曲线练习
 * 一个波浪视图
 *
 */
public class BeisaierWaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beisaier_wave);
        BeisaierWaveView beisaierWaveView = findViewById(R.id.bwv);
        beisaierWaveView.startAnimaton();

        BeisaierWaveView2 beisaierWaveView2 = findViewById(R.id.bwv2);
        beisaierWaveView2.startAnimaton();

    }
}
