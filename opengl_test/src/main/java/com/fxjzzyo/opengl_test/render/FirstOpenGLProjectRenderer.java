package com.fxjzzyo.opengl_test.render;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;

/**
 * @author fanlulin
 * @since 2019-09-24
 */
public class FirstOpenGLProjectRenderer implements GLSurfaceView.Renderer {


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        // 清屏用的颜色
        glClearColor(1.0f,0.0f,0.0f,0.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // 设置视图尺寸
        glViewport(0,0,width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        // 清屏
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
