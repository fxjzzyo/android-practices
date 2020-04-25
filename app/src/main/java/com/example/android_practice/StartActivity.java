package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}
