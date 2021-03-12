package com.example.opengl;

import android.content.Context;
import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MiSurfacePersonalizado extends GLSurfaceView {

    MiRenderizado miRenderizado;

    public MiSurfacePersonalizado(Context context) {
        super(context);
        miRenderizado = new MiRenderizado();
        setRenderer(miRenderizado);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        queueEvent(new Runnable() {
            @Override
            public void run() {
                miRenderizado.setColor(event.getX()/getWidth(), event.getY()/getHeight() , 1f);
            }
        });
    return true;
    }

    class MiRenderizado implements GLSurfaceView.Renderer{

         private float verde = Color.GREEN;
         private float rojo =Color.RED;
         private float azul = Color.BLUE;

         private Cuadrado cuadrado ;
         private  Triangulo triangulo ;

         public void setColor(float  r ,float a , float az){

             this.verde = a;
             this.rojo = r ;
             this.azul = az;

         }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            cuadrado = new Cuadrado();
            triangulo = new Triangulo();

            gl.glClearColor(rojo, verde, azul, 1.0f);
            gl.glClearDepthf(1.0f);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glDepthFunc(GL10.GL_LEQUAL);
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
            gl.glShadeModel(GL10.GL_SMOOTH);
            gl.glDisable(GL10.GL_DITHER);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            if (height == 0){
                height = 1;
            }
            float aspecto = (float) width/height;
            gl.glViewport(0 , 0 , width , height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl , 45 , aspecto , 0.1f ,100f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT|  GL10.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();
            gl.glTranslatef(1, 0 , -25);
            triangulo.draw(gl);
        }
    }


}
