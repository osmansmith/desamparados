package com.example.desamparados.Adaptador;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.desamparados.Clases.Aviso;
import com.example.desamparados.R;


import java.util.ArrayList;



public class AdapterAvisos extends RecyclerView.Adapter<AdapterAvisos.ViewHolder> implements View.OnClickListener {

    private ArrayList<Aviso> listaAvisos;
    private View.OnClickListener click;

    public AdapterAvisos(Context context, ArrayList<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONECTA LA CLASE ADAPTADOR CON EL LAYOUT list_avisos
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_avisos, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //HACE CONExION ENTRE EL ADAPTADOR Y LA CLASE ViewHolder()
        holder.nombre.setText(listaAvisos.get(position).getNombre());
        holder.Descripcion.setText(listaAvisos.get(position).getDescripcion());
        holder.imagen.setImageResource(listaAvisos.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        //TOMA EL TAMAÑO DE LA LISTA listaAvisos
        return listaAvisos.size();
    }
    //PARA HACER CLICK
    public void setOnClickListener(View.OnClickListener click){
        this.click = click;
    }
    @Override
    public void onClick(View v) {
        if(click!=null){
           click.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //HACE REFERENCIA A LO UTILIZADO EN EL LAYOUT LIST_AVISO
        ImageView imagen;
        TextView nombre, Descripcion;

        public ViewHolder(View view) {
            super(view);
            imagen = (ImageView) itemView.findViewById(R.id.imageView6);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            Descripcion = (TextView) itemView.findViewById(R.id.descripcion);
        }

    }
}
