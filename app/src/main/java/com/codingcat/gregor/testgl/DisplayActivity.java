package com.codingcat.gregor.testgl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class DisplayActivity extends Activity
{
    private GLSurfaceView m_glView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        m_glView = new SGLSurfaceView( this );

        setContentView( m_glView );
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        m_glView.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        m_glView.onResume();
    }
}
