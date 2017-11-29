package com.luoxiang.glhexagram;

/**
 * projectName: 	    GLhexagram
 * packageName:	        com.luoxiang.glhexagram
 * className:	        Hexagram
 * author:	            Luoxiang
 * time:	            2017/11/29	10:28
 * desc:	            TODO
 *
 * svnVersion:	        $Rev
 * upDateAuthor:	    Vincent
 * upDate:	            2017/11/29
 * upDateDesc:	        TODO
 */


public class Hexagram {
    float yAngle = 0;
    float xAngle = 0;
    final float UNIT_SIZE = 1;

    public Hexagram(HexagramSurfaceView surfaceView , float R , float r , float z){
        initVertexData(R , r , z);
        initShader(surfaceView);
    }

    /**
     * 初始化着色器
     * @param surfaceView
     */
    private void initShader(HexagramSurfaceView surfaceView) {


    }

    /**
     * 初始化顶点数据的方法
     * @param R
     * @param r
     * @param z
     */
    private void initVertexData(float R, float r, float z) {

    }

    public void drawSelf(){

    }
}
