package com.fxjzzyo.opengl_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.Toast;

import com.fxjzzyo.opengl_test.render.FirstOpenGLProjectRenderer;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;
    private boolean rendererSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new GLSurfaceView(this);
        // 判断是否支持OpenGL2.0
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        if(supportsEs2){
            mGLSurfaceView.setEGLContextClientVersion(2);
            mGLSurfaceView.setRenderer(new FirstOpenGLProjectRenderer());
            rendererSet = true;
        }else{
            Toast.makeText(this,"不支持openGL2.0", Toast.LENGTH_SHORT).show();
        }
        setContentView(mGLSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rendererSet){
            mGLSurfaceView.onResume();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if(rendererSet){
            mGLSurfaceView.onPause();
        }
    }
}
