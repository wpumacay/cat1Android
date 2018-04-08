package com.codingcat.gregor.testgl;


import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class SShaderManager
{
    private static final String GL_TAG = "Tag_Shader";

    public static SShaderManager INSTANCE = null;

    public HashMap< String, SShader > programObjs;

    public static SShaderManager create( Context context )
    {
        if ( SShaderManager.INSTANCE == null )
        {
            SShaderManager.INSTANCE = new SShaderManager( context );
        }

        return SShaderManager.INSTANCE;
    }

    public SShaderManager( Context context )
    {
        programObjs = new HashMap< String, SShader >();

        _loadShaders( context );
    }

    private void _loadShaders( Context context )
    {
        Log.i( SShaderManager.GL_TAG, "Initialized loading shaders" );
        // Load shaders from resources folder
        Resources _resources = context.getResources();

        int _vsShaderId, _fsShaderId, _programId;

        // Load basic2d shader
        _vsShaderId = _loadShaderById( _resources, R.raw.basic2d_vs, GLES20.GL_VERTEX_SHADER );
        _fsShaderId = _loadShaderById( _resources, R.raw.basic2d_fs, GLES20.GL_FRAGMENT_SHADER );
        _programId = createProgram( _vsShaderId, _fsShaderId );
        programObjs.put( "basic2d", new SShader( _programId ) );

        Log.i( SShaderManager.GL_TAG, "Done loading shaders" );
    }

    private int _loadShaderById( Resources resources, int id, int shaderType )
    {
        InputStream _is = resources.openRawResource( id );
        String _shaderCode = _stream2string( _is );

        int _shaderId = GLES20.glCreateShader( shaderType );

        GLES20.glShaderSource( _shaderId, _shaderCode );
        GLES20.glCompileShader( _shaderId );

        int[] _success = new int[1];

        GLES20.glGetShaderiv( _shaderId, GLES20.GL_COMPILE_STATUS, _success, 0 );
        if ( _success[0] == GLES20.GL_FALSE )
        {
            String _shaderTypeStr = ( shaderType == GLES20.GL_VERTEX_SHADER ) ? "VertexShader" : "FragmentShader";
            Log.e( SShaderManager.GL_TAG, "Could not compile shader " + _shaderTypeStr + " : " );
            Log.e( SShaderManager.GL_TAG, GLES20.glGetShaderInfoLog( _shaderId ) );
            Log.e( SShaderManager.GL_TAG, "ShaderCode: " );
            Log.e( SShaderManager.GL_TAG, _shaderCode );
            GLES20.glDeleteShader( _shaderId );
            _shaderId = 0;
        }

        return _shaderId;
    }

    public int createProgram( int vsId, int fsId )
    {
        int _programId = GLES20.glCreateProgram();

        GLES20.glAttachShader( _programId, vsId );
        GLES20.glAttachShader( _programId, fsId );
        GLES20.glLinkProgram( _programId );

        int[] _success = new int[1];

        GLES20.glGetProgramiv( _programId, GLES20.GL_LINK_STATUS, _success, 0 );
        if ( _success[0] == GLES20.GL_FALSE )
        {
            Log.e( SShaderManager.GL_TAG, "Could not link program: " );
            Log.e( SShaderManager.GL_TAG, GLES20.glGetProgramInfoLog( _programId ) );
            _programId = 0;
        }

        return _programId;
    }

    private String _stream2string( InputStream iStream )
    {
        BufferedReader _reader = new BufferedReader( new InputStreamReader( iStream ) );
        StringBuilder _sBuilder = new StringBuilder();

        String _line = null;

        try
        {
            while ( ( _line = _reader.readLine() ) != null )
            {
                _sBuilder.append( _line ).append( '\n' );
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                iStream.close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }

        return _sBuilder.toString();
    }
}
