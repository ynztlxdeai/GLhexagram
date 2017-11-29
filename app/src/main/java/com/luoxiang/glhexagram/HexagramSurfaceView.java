package com.luoxiang.glhexagram;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * projectName: 	    GLhexagram
 * packageName:	        com.luoxiang.glhexagram
 * className:	        HexagramSurfaceView
 * author:	            Luoxiang
 * time:	            2017/11/29	10:17
 * desc:	            TODO
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2017/11/29
 * upDateDesc:	        TODO
 */


public class HexagramSurfaceView
        extends GLSurfaceView
{
    //角度缩放比例
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    //场景渲染器
    private SceneRenderer mSceneRenderer;
    //上次的触控位置Y
    private float         mPreviousY;
    //上次的触控位置X
    private float         mPreviousX;

    public HexagramSurfaceView(Context context) {
        this(context, null);
    }

    public HexagramSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //设置使用OpenGL ES 2.0
        setEGLContextClientVersion(2);
        //渲染器初始化
        mSceneRenderer = new SceneRenderer();
        //设置渲染器
        setRenderer(mSceneRenderer);
        //模式是连续不断地
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dy = y - mPreviousY;
                float dx = x - mPreviousX;

                break;

            default:
                break;
        }

        return true;
    }

    private class SceneRenderer
            implements GLSurfaceView.Renderer
    {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    }
}
