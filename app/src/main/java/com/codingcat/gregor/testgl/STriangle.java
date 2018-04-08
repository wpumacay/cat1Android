package com.codingcat.gregor.testgl;


import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class STriangle
{
    private FloatBuffer m_vertexBuffer;

    private float m_vertices[] = { 0.0f,  0.5f, 0.0f,
                                  -0.5f, -0.5f, 0.0f,
                                   0.5f, -0.5f, 0.0f };

    private float m_color[] = { 0.63f, 0.76f, 0.22f, 1.0f };

    public STriangle()
    {
        ByteBuffer _bb = ByteBuffer.allocateDirect( m_vertices.length * 4 );
        _bb.order( ByteOrder.nativeOrder() );

        m_vertexBuffer = _bb.asFloatBuffer();
        m_vertexBuffer.put( m_vertices );
        m_vertexBuffer.position( 0 );
    }

    public void render( SShader shader )
    {
        // Enable shader attribute
        int _positionAttribHandle = GLES20.glGetAttribLocation( shader.id(), "position" );
        GLES20.glEnableVertexAttribArray( _positionAttribHandle );
        // bind data
        GLES20.glVertexAttribPointer( _positionAttribHandle, 3,
                                      GLES20.GL_FLOAT, false,
                                      3 * 4, m_vertexBuffer );

        shader.setVec4( "u_color", m_color );

        GLES20.glDrawArrays( GLES20.GL_TRIANGLES, 0, 3 );

        GLES20.glDisableVertexAttribArray( _positionAttribHandle );
    }

}













