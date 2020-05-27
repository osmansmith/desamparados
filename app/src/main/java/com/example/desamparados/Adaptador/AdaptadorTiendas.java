package com.example.desamparados.Adaptador;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desamparados.Clases.Tiendas;
import com.example.desamparados.R;
import java.util.ArrayList;



public class AdaptadorTiendas extends RecyclerView.Adapter<AdaptadorTiendas.ViewHolder> {

    private ArrayList<Tiendas> tiendas;

    public AdaptadorTiendas(Context context, ArrayList<Tiendas> tienda) {
        this.tiendas = tienda;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_avisos,parent,false);
      return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //HACE CONExION ENTRE EL ADAPTADOR Y LA CLASE ViewHolder()
        holder.nombre.setText(tiendas.get(position).getNombre());
        holder.Descripcion.setText(tiendas.get(position).getDescripcion());
        holder.imagen.setImageResource(tiendas.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return tiendas.size();
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
