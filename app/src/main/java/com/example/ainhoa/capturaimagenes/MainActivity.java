package com.example.ainhoa.capturaimagenes;

import android.support.v7.app.AppCompatActivity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //variables de la camara
    private Camera mCamera = null;
    private CameraView mCameraView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mCamera = Camera.open();//you can use open(int) to use different cameras
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error en la camara", Toast.LENGTH_LONG).show();
        }

        if (mCamera != null) {
            mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
            FrameLayout camera_view = (FrameLayout) findViewById(R.id.camara_vista);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }

        //botones
        ImageButton imgClose = (ImageButton) findViewById(R.id.imgClose);
        ImageButton imgSave= (ImageButton)findViewById(R.id.imgCaptura);

        //metodo para guardar la imagen
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"guardar imagen",Toast.LENGTH_LONG).show();
                //guardar la imagen

            }
        });

        //metodo de Onclick
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);

            }

        });



    }

}

