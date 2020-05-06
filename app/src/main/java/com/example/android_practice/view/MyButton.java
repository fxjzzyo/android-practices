package com.example.android_practice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;


public class MyButton extends AppCompatButton {

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("ricky", "dispatchTouchEvent:action--"+event.getAction()+"---view:MyButton");
		return super.dispatchTouchEvent(event);
//		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("ricky", "onTouchEvent:action--"+event.getAction()+"---view:MyButton");
		return super.onTouchEvent(event);
//		return true;
	}
	
}
