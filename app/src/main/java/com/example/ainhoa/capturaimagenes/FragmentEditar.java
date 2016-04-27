package com.example.ainhoa.capturaimagenes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;



public class FragmentEditar extends Fragment implements View.OnClickListener {

    private FragmentEditar drawView;
    private ImageButton currPaint,drawBtn,newBtn,saveBtn,eraseBt;

    //medidas del pincel
    private float smallBrush, mediumBrush, largeBrush;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        drawView = (FragmentEditar)rootview.findViewById(R.id.drawing);

        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors2);

        //pinceles
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        //referencia al boton dibujar
        drawBtn = (ImageButton)findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);

        //referencia al boton nuevo
        newBtn = (ImageButton)findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        //referencia al boton guardar
        saveBtn=(ImageButton)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

        //referencia al bonto limpiar
        eraseBt=(ImageButton)findViewById(R.id.erase_btn);
        eraseBt.setOnClickListener(this);

        //escogemos el valor
        currPaint = (ImageButton) paintLayout.getChildAt(5);

        //y cambiamos el boton de creacion con
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //metodo paint clicke
    public void paintClicked(View view){

        if(view!=currPaint){

            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();

            drawView.setColor(color);
            //e implementamos para que el boton vuelva a la normalidad
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currPaint=(ImageButton)view;
        }

    }
    //on click  para dibujar

    @Override
    public void onClick(View v) {
        //modificado a v
        if(v.getId()==R.id.draw_btn){
            //para elegir size de pincel
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Tamaño Brocha:");
            //creacion del menu de pincel
            brushDialog.setContentView(R.layout.brush_chooser);
            //completamos el dialogo con
            brushDialog.show();

            //on click del menu de pincel
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });

            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });


        }else if(v.getId()==R.id.new_btn){
            //dialogo de nuevo botn para dibujar
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("Nuevo Dibujo");
            newDialog.setMessage("¿Empezamos de Nuevo?");
            newDialog.setPositiveButton("Si", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();

        }else if(v.getId()==R.id.save_btn){
            //guardar el dibujo
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Guardar Dibujo");
            saveDialog.setMessage("Guardar dibujo");
            saveDialog.setPositiveButton("Si", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //Guardamos el dibujo
                    drawView.setDrawingCacheEnabled(true);


                    String imgSaved = MediaStore.Images.Media.insertImage(
                            getContentResolver(), drawView.getDrawingCache(),"foto.png","dibujo");

                    //definimos si se guarda o no la imagen
                    if(imgSaved!=null){
                        Toast.makeText(getApplicationContext(),
                                "Guardamos la imagen!", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "La imagen no se ha guardado", Toast.LENGTH_SHORT).show();

                    }
                    //y limpiamos el cache para evitar reescribir
                    drawView.destroyDrawingCache();


                }
            });
            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            saveDialog.show();

        }else if(v.getId()==R.id.erase_btn){
            Toast.makeText(getApplicationContext(),"Vamos a Encalar", Toast.LENGTH_SHORT).show();
            //limpiamos la vista
            drawView.startNew();

        }
    }
}
