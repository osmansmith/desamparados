package com.example.desamparados.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.desamparados.Clases.Aviso;
import com.example.desamparados.Clases.TipoAviso;
import com.example.desamparados.Clases.TipoMascota;
import com.example.desamparados.FireBase.AvisoFirebase;
import com.example.desamparados.FireBase.TipoAvisoFirebase;
import com.example.desamparados.FireBase.TipoMascotaFirebase;
import com.example.desamparados.MainActivity;
import com.example.desamparados.MapsActivity;
import com.example.desamparados.R;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.zelory.compressor.Compressor;

public class CrearAviso extends AppCompatActivity implements View.OnClickListener {
    public static double latitude;
    public static double longitude;
    EditText nombre;
    EditText descripcion;
    ImageView img;
    Spinner spinner_tipo_aviso;
    Spinner spinner_tipo_mascota;
    Button boton_crear_aviso;
    public static EditText latitud;
    public static EditText longitud;
    public static EditText direccion;
    Button btn_img;
    Button boton_mapa;

    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private Uri photoURI;
    public String mCurrentPhotoPath;
    Bitmap bitmap;
    byte[] thumb_byte = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_aviso);
        nombre=(EditText) findViewById(R.id.nombre);
        descripcion=(EditText) findViewById(R.id.descripcion);
        latitud=(EditText) findViewById(R.id.latitud);
        longitud=(EditText) findViewById(R.id.longitud);
        direccion=(EditText) findViewById(R.id.direccion);
        boton_mapa = (Button) findViewById(R.id.boton_mapa) ;
        boton_mapa.setOnClickListener(this);
        btn_img = (Button) findViewById(R.id.btn_img);
        btn_img.setOnClickListener(this);
        boton_crear_aviso = (Button) findViewById(R.id.boton_crear_aviso);
        boton_crear_aviso.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
        spinner_tipo_aviso = (Spinner) findViewById(R.id.spinner_tipo_aviso);
        spinner_tipo_mascota = (Spinner) findViewById(R.id.spinner_tipo_mascota);

        llenarSpinnerTipoAviso();
        llenarSpinnerTipoMascota();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_img:
                showOptions();
                //CropImage.startPickImageActivity(CrearAviso.this);
                break;
            case R.id.boton_mapa:
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                break;
            case R.id.boton_crear_aviso:
                this.crearAviso();
                break;
        }
    }



    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(CrearAviso.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (option[which] == "Tomar foto") {
                    openCamera();
                } else if (option[which] == "Elegir de galeria") {
                    openGallery();

                } else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                photoURI = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                //Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.example.android.fileprovider", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
            }
        }
    }


    public void openCamera() {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {


                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            225);
                }


                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            226);
                }
            } else {
                dispatchTakePictureIntent();
            }

    }



    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,100 );
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath= image.getAbsolutePath();

        return image;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                img.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==100){
            photoURI = data.getData();
            img.setImageURI(photoURI);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
            } catch (IOException e) {
                e.printStackTrace();
            }
            img.setImageBitmap(bitmap);
            //comprimiendo imagen
            /*try {
                File url = new File(photoURI.getPath());
                Bitmap bitmap;
                bitmap = new Compressor(this)
                        .setMaxHeight(640)
                        .setMaxWidth(480)
                        .setQuality(480)
                        .compressToBitmap(url);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
                thumb_byte = byteArrayOutputStream.toByteArray();


            } catch (IOException e) {
                e.printStackTrace();
            }*/




        }
       /* if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            //recortar imagen

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(640, 480)
                    .setAspectRatio(2, 1).start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                File url = new File(resultUri.getPath());
                //mostrando imagen
                //Picasso.get().load(resultUri).into(img);


                //comprimiendo imagen
                try {
                    Bitmap bitmap;
                    bitmap = new Compressor(this)
                            .setMaxHeight(640)
                            .setMaxWidth(480)
                            .setQuality(480)
                            .compressToBitmap(url);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
                thumb_byte = byteArrayOutputStream.toByteArray();

                }
        } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            System.out.println("error en la imagen");
        }*/

    }






    public void llenarSpinnerTipoAviso() {
        TipoAvisoFirebase f = new TipoAvisoFirebase();
        f.getTipoAvisos(getApplicationContext(),spinner_tipo_aviso);
    }

    public void llenarSpinnerTipoMascota(){
        TipoMascotaFirebase f = new TipoMascotaFirebase();
        f.getTipoMascotas(getApplicationContext(),spinner_tipo_mascota);
    }

    public void crearAviso(){
        File url = new File(photoURI.getPath());


        Aviso aviso = new Aviso();
        aviso.setNombre(this.nombre.getText().toString().trim());
        aviso.setDescripcion(this.descripcion.getText().toString().trim());
        aviso.setLatitud(Double.parseDouble(this.latitud.getText().toString().trim()));
        aviso.setLongitud(Double.parseDouble(this.longitud.getText().toString().trim()));
        aviso.setEstado(1);
        aviso.setDireccion(this.direccion.getText().toString());
        aviso.setTipoAviso((TipoAviso) spinner_tipo_aviso.getSelectedItem());
        aviso.setTipoMascota((TipoMascota) spinner_tipo_mascota.getSelectedItem());
        AvisoFirebase avisoFirebase = new AvisoFirebase();
        if(avisoFirebase.insertarAviso(aviso,thumb_byte)){
            Toast.makeText(this, "Aviso Creado!",Toast.LENGTH_LONG).show();
            Intent i =new Intent(this, MainActivity.class);
            startActivity(i);

        }
        else{
            Toast.makeText(this, "Aviso no pudo ser ingresado :c",Toast.LENGTH_LONG).show();
        }

    }




}



