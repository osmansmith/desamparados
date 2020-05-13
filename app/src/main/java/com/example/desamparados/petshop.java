package com.example.desamparados;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class petshop extends Fragment {

    private PetshopViewModel mViewModel;
    private ListView listview;

    public static petshop newInstance() {
        return new petshop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.petshop_fragment,
                container, false);
        listview = (ListView)view.findViewById(R.id.listPetshop);
        //return inflater.inflate(R.layout.aviso_fragment, container, false);

        initUI(view);


        return view;
    }
    private void initUI(View v){
        FloatingActionButton fab = v.findViewById(R.id.btn_petshop);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PetshopViewModel.class);

        // Aqui se puede usar el viewModel

        List<String> shop = new ArrayList<String>();
        shop.add("TiendaPet.cl");
        shop.add("Supermercado de Mascotas");
        shop.add("Enanitos del Elqui Criadero");
        shop.add("Decoralia");
        shop.add("Acani \"enchula tu mascota\" ");

        listview.setAdapter(new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, shop));
    }

}
