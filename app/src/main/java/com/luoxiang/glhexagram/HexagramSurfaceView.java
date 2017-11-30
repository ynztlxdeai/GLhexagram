package com.luoxiang.glhexagram;

import android.content.Context;
import android.opengl.GLES20;
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
                for(Hexagram hexagram : mSceneRenderer.mHexagrams){
                    hexagram.yAngle += dx * TOUCH_SCALE_FACTOR;
                    hexagram.xAngle += dy * TOUCH_SCALE_FACTOR;
                }
                break;
        }
        mPreviousY = y ;
        mPreviousX = x ;
        return true;
    }

    private class SceneRenderer
            implements GLSurfaceView.Renderer
    {
        Hexagram[] mHexagrams = new Hexagram[6];

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
           //设置背景颜色RGBA
            GLES20.glClearColor(0.5f ,0.5f ,0.5f ,1.0f );
            for (int i = 0; i < mHexagrams.length; i++) {
                //正交投影数据
                //mHexagrams[i] = new Hexagram(HexagramSurfaceView.this , 0.2f , 0.5f , -0.3f * i);
                //透视投影数据
                mHexagrams[i] = new Hexagram(HexagramSurfaceView.this , 0.4f , 1.0f , 1.0f * i);
            }
            //开启深度测试
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置视窗位置
            GLES20.glViewport(0 , 0 , width , height);
            float ratio = (float) width / height;


            //设置正交投影
            //MatrixState.setProjectionOrtho(-ratio , ratio , -1 , 1 , 1 , 10);
            //设置摄像机位置
            //MatrixState.setCamera(0 , 0 , 3f , 0 , 0 , 0f , 0f , 1.0f , 0.0f);


            //透视投影
            MatrixState.setProjectFrustum(-ratio * 0.4f , ratio * 0.4f , -1 * 0.4f , 1 * 0.4f , 1 , 50);
            //设置摄像机位置
            MatrixState.setCamera(0f , 0f , 6f , 0f , 0f , 0f , 0f , 1.0f , 0.0f);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //清除深度和颜色缓冲
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            //绘制六角星
            for (Hexagram hexagram : mHexagrams){
                hexagram.drawSelf();
            }
        }
    }
}
