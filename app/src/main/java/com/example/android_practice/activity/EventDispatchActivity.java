package com.example.android_practice.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.android_practice.R;
import com.example.android_practice.view.MyButton;

/**
 * View事件分发练习
 */
public class EventDispatchActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private Button button1;
    private LinearLayout layout;
    private MyButton mButton2;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);

        button1 = (Button)findViewById(R.id.btn);
        layout = findViewById(R.id.ll_layout);
        mButton2 = findViewById(R.id.my_btn2);

//        layout.setOnTouchListener(this);
        button1.setOnTouchListener(this);

        layout.setOnClickListener(this);
        button1.setOnClickListener(this);

        mButton2.setOnClickListener(this);
        mButton2.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, final MotionEvent event) {
        Log.i("ricky", "OnTouchListener:acton--"+event.getAction()+"----view:"+v);
//        return false;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mButton2.onTouchEvent(event);
                Log.i("ricky", "event:acton--"+event.getAction());
            }
        },3000);


        return true;
    }

    @Override
    public void onClick(View v) {
        Log.i("ricky", "OnClickListener----view:"+v);
    }
}
