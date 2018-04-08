package com.codingcat.gregor.testgl;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SGLRenderer implements GLSurfaceView.Renderer
{
    private STriangle m_tTriangle;
    private SSquare m_tSquare;

    private SShader m_tShader;

    private Context m_contextRef;

    public SGLRenderer( Context context )
    {
        m_contextRef = context;
    }

    @Override
    public void onSurfaceCreated( GL10 unused, EGLConfig config )
    {
        // Create managers
        SShaderManager.create( m_contextRef );

        // Set clear color
        GLES20.glClearColor( 0.0f, 1.0f, 1.0f, 1.0f );

        // Create some drawing primitives
        m_tTriangle = new STriangle();
        m_tSquare = new SSquare();

        // Get a reference to the basic 2d drawing shader
        m_tShader = SShaderManager.INSTANCE.programObjs.get( "basic2d" );
    }

    @Override
    public void onDrawFrame( GL10 unused )
    {
        GLES20.glClear( GLES20.GL_COLOR_BUFFER_BIT );

        m_tShader.bind();

        m_tTriangle.render( m_tShader );

        m_tShader.unbind();
    }

    @Override
    public void onSurfaceChanged( GL10 unused, int width, int height )
    {
        GLES20.glViewport( 0, 0, width, height );
    }
}
