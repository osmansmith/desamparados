package com.Desampara2.desamparados.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//import com.airbnb.lottie.utils.Utils;
import com.Desampara2.desamparados.Clases.LoadingDialog;
import com.Desampara2.desamparados.Clases.Network;
import com.Desampara2.desamparados.FireBase.AvisoFirebase;
import com.Desampara2.desamparados.R;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    AlertDialog dialog;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_avisos, container,false);
        //layout = (LinearLayout) vista.findViewById(R.id.layout_progress);
        recyclerView=(RecyclerView) vista.findViewById(R.id.recyclerview);
        return vista;

    }

    @Override
    public void onResume() {
        if(Network.isOnline(getContext())){
            LoadingDialog loadingDialog = new LoadingDialog(this.getActivity());
            MostraLista(loadingDialog);
        }else{
            Toast.makeText(getContext(),"No hay conexión a internet", Toast.LENGTH_LONG).show();
        }
        super.onResume();
    }

    public boolean MostraLista(LoadingDialog loadingDialog){
       AvisoFirebase avisoFirebase = new AvisoFirebase();

       return avisoFirebase.getAvisos(getContext(),recyclerView,loadingDialog);
    }



}
