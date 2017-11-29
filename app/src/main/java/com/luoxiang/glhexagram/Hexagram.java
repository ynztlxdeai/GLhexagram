package com.luoxiang.glhexagram;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

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
    //
    int vCount;
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;

    public Hexagram(HexagramSurfaceView surfaceView, float R, float r, float z) {
        initVertexData(R, r, z);
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
        List<Float> flist     = new ArrayList<>();
        float       tempAngle = 360 / 6;
        //循环生成六角形各个三角形的顶点坐标
        for (float angle = 0; angle < 360; angle += tempAngle) {
            //第一个点的X Y Z
            flist.add(0f);
            flist.add(0f);
            flist.add(z);

            //第二个点的X坐标
            flist.add((float) (R * UNIT_SIZE * Math.cos(Math.toRadians(angle))));
            //第二个点的Y坐标
            flist.add((float) (R * UNIT_SIZE * Math.sin(Math.toRadians(angle))));
            //第二个点的Z坐标
            flist.add(z);

            //第三个点的X坐标
            flist.add((float) (r * UNIT_SIZE * Math.cos(Math.toRadians(angle + tempAngle / 2))));
            //第三个点的Y坐标
            flist.add((float) (r * UNIT_SIZE * Math.sin(Math.toRadians(angle + tempAngle / 2))));
            //第三个点的Z坐标
            flist.add(z);

            //-----------------------------------------------------------------------------------

            //第一个点的X Y Z
            flist.add(0f);
            flist.add(0f);
            flist.add(z);

            //第二个点的X坐标
            flist.add((float) (r * UNIT_SIZE * Math.cos(Math.toRadians(angle))));
            //第二个点的Y坐标
            flist.add((float) (r * UNIT_SIZE * Math.sin(Math.toRadians(angle))));
            //第二个点的Z坐标
            flist.add(z);

            //第三个点的X坐标
            flist.add((float) (R * UNIT_SIZE * Math.cos(Math.toRadians(angle + tempAngle / 2))));
            //第三个点的Y坐标
            flist.add((float) (R * UNIT_SIZE * Math.sin(Math.toRadians(angle + tempAngle / 2))));
            //第三个点的Z坐标
            flist.add(z);
        }

        vCount = flist.size() / 3;
        //顶点坐标数组
        float[] vertexArray = new float[flist.size()];
        for (int i = 0; i < vCount; i++) {
            vertexArray[i * 3] = flist.get(i * 3);
            vertexArray[i * 3 + 1] = flist.get(i * 3 + 1);
            vertexArray[i * 3 + 2] = flist.get(i * 3 + 2);
        }
        //一个float = 4byte
        ByteBuffer vbb = ByteBuffer.allocate(vertexArray.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertexArray);
        mVertexBuffer.position(0);

        //顶点着色数据初始化
        float[] colorArray = new float[vCount * 4];
        for (int i = 0; i < vCount; i++) {
            if (i % 3 == 0){
                //中心点是白色 RGBA(1,1,1,0)
                colorArray[i * 4] = 1;
                colorArray[i * 4 + 1] = 1;
                colorArray[i * 4 + 2] = 1;
                colorArray[i * 4 + 3] = 0;
            }else {
                //边上是蓝色 RGBA(0.45,0.75,0.75,0)
                colorArray[i * 4] = 0.45f;
                colorArray[i * 4 + 1] = 0.75f;
                colorArray[i * 4 + 2] = 0.75f;
                colorArray[i * 4 + 3] = 0;
            }
        }
        ByteBuffer cbb = ByteBuffer.allocate(colorArray.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(colorArray);
        mColorBuffer.position(0);

    }

    public void drawSelf() {

    }
}
