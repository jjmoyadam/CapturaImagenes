package com.example.ainhoa.capturaimagenes;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ainhoa on 25/04/2016.
 */
public class GuardarImagen implements PictureCallback  {

    //variables
    private final Context context;

    //constructor
    public GuardarImagen(Context context) {


        this.context = context;
    }

    //on picturetaken

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {


    }
}
