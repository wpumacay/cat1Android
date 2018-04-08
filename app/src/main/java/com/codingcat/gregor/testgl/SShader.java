package com.codingcat.gregor.testgl;


import android.opengl.GLES20;

public class SShader
{
    private int m_id;

    public SShader( int id )
    {
        m_id = id;
    }

    public int id() { return m_id; }

    public void bind()
    {
        GLES20.glUseProgram( m_id );
    }

    public void unbind()
    {
        GLES20.glUseProgram( 0 );
    }

    public void setInt( String uName, int value )
    {
        GLES20.glUniform1i( GLES20.glGetUniformLocation( m_id, uName ), value );
    }

    public void setFloat( String uName, float value )
    {
        GLES20.glUniform1f( GLES20.glGetUniformLocation( m_id, uName ), value );
    }

    public void setVec2( String uName, float[] vec )
    {
        GLES20.glUniform2fv( GLES20.glGetUniformLocation( m_id, uName ), 1, vec, 0 );
    }

    public void setVec3( String uName, float[] vec )
    {
        GLES20.glUniform3fv( GLES20.glGetUniformLocation( m_id, uName ), 1, vec, 0 );
    }

    public void setVec4( String uName, float[] vec )
    {
        GLES20.glUniform4fv( GLES20.glGetUniformLocation( m_id, uName ), 1, vec, 0 );
    }

    public void setMat4( String uName, float[] mat )
    {
        GLES20.glUniformMatrix4fv( GLES20.glGetUniformLocation( m_id, uName ), 1, false, mat, 0 );
    }
}





