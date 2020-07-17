package com.Desampara2.desamparados.Clases;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.Desampara2.desamparados.R;

public class LoadingDialog {
    Activity activity;
    AlertDialog dialog;


    public LoadingDialog(Activity myActivity){
        this.activity = myActivity;
    }

    public void startLoadingDialog(String option){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        if(option.equals("avisos")) {
            builder.setView(inflater.inflate(R.layout.dialog_avisos, null));
        }else{
            builder.setView(inflater.inflate(R.layout.dialog_tiendas, null));
        }builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();

    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
