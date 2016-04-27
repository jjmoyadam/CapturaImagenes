package com.example.ainhoa.capturaimagenes;

import android.content.Context;
//libreria de hardware
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Ainhoa on 25/04/2016.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    //variables para el manejo
    private SurfaceHolder mHolder;
    private Camera mCamera;


    //constuctor
    public CameraView(Context context, Camera camera) {
        super(context);

        //camara
        mCamera = camera;
        //orientacion
        mCamera.setDisplayOrientation(90);
        //manejador de la vista de la camara
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    // metodos para manejar la vista surface

    //creacion
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        //vista previa de la camara
        try {

            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //si la orientacion de la aplicacion cambia
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {


        //check if the surface is ready to receive camera data
        if (mHolder.getSurface() == null)
            return;

        try{
            mCamera.stopPreview();
        } catch (Exception e){

            Toast.makeText(getContext(),"error en la camara al parar",Toast.LENGTH_LONG).show();
        }

        //creacion de nuevo de la vista de
        try{
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (IOException e) {
            Toast.makeText(getContext(),"error en la camara al crear la nueva vista",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {


        //parada de la camara
        mCamera.stopPreview();
        mCamera.release();
    }




}
