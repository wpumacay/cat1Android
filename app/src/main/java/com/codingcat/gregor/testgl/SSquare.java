package com.codingcat.gregor.testgl;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class SSquare
{
    private FloatBuffer m_vertexBuffer;
    private ShortBuffer m_indexBuffer;

    private float m_vertices[] = { -0.5f,  0.5f, 0.0f,
                                   -0.5f, -0.5f, 0.0f,
                                    0.5f, -0.5f, 0.0f,
                                    0.5f,  0.5f, 0.0f };

    private short m_indices[] = { 0, 1, 2,
                                  0, 2, 3 };

    public SSquare()
    {
        // Create byte-buffer for vertices
        ByteBuffer _vbb = ByteBuffer.allocateDirect( m_vertices.length * 4 );
        _vbb.order( ByteOrder.nativeOrder() );

        // Initialize vertices buffer
        m_vertexBuffer = _vbb.asFloatBuffer();
        m_vertexBuffer.put( m_vertices );
        m_vertexBuffer.position( 0 );

        // Create byte-buffer for indices
        ByteBuffer _ibb = ByteBuffer.allocateDirect( m_indices.length * 2 );
        _ibb.order( ByteOrder.nativeOrder() );

        // Initialize buffer for indices
        m_indexBuffer = _ibb.asShortBuffer();
        m_indexBuffer.put( m_indices );
        m_indexBuffer.position( 0 );
    }
}












