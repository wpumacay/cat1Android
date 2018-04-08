package com.codingcat.gregor.testgl;

import android.content.Context;
import android.opengl.GLSurfaceView;


public class SGLSurfaceView extends GLSurfaceView
{
    private final SGLRenderer m_renderer;

    public SGLSurfaceView( Context context )
    {
        super( context );

        // Create the gl-context
        setEGLContextClientVersion( 2 );

        // Instantiate the renderer
        m_renderer = new SGLRenderer( context );
        setRenderer( m_renderer );

        // Render continuously in this case
        setRenderMode( GLSurfaceView.RENDERMODE_CONTINUOUSLY );
    }

}
