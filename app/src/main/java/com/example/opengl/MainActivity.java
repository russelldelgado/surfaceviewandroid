package com.example.opengl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private  MiSurfacePersonalizado glVista;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl = findViewById(R.id.ocupando);
        glVista = new MiSurfacePersonalizado(this);
        rl.addView(glVista);


    }


    @Override
    protected void onPause() {
        super.onPause();
        glVista.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glVista.onResume();
    }
}