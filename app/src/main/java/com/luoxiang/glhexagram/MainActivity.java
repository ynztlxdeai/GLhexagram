package com.luoxiang.glhexagram;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity
        extends AppCompatActivity
{

    private HexagramSurfaceView mHexagramSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mHexagramSurfaceView = new HexagramSurfaceView(this);
        mHexagramSurfaceView.requestFocus();
        mHexagramSurfaceView.setFocusableInTouchMode(true);
        setContentView(mHexagramSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHexagramSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHexagramSurfaceView.onPause();
    }
}
