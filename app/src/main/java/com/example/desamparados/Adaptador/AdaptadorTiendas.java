package com.example.desamparados.Adaptador;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.desamparados.Activity.DetalleAvisos;
import com.example.desamparados.Activity.DetalleTiendas;
import com.example.desamparados.Clases.Tiendas;
import com.example.desamparados.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class AdaptadorTiendas extends RecyclerView.Adapter<AdaptadorTiendas.ViewHolder> {

    private ArrayList<Tiendas> tiendas;
    private Context context;

    public AdaptadorTiendas(Context context, ArrayList<Tiendas> tienda) {
        this.tiendas = tienda;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tiendas,parent,false);
      return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //HACE CONExION ENTRE EL ADAPTADOR Y LA CLASE ViewHolder()
        final Tiendas tienda = tiendas.get(position);
        holder.nombre.setText(tiendas.get(position).getNombre());
        holder.Descripcion.setText(tiendas.get(position).getDescripcion());
        Picasso.get().load(tiendas.get(position).getImagen_firebase()).into(holder.imagen);

        holder.layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetalleTiendas.class);
                i.putExtra("nombre", tienda.getNombre());
                i.putExtra("descripcion", tienda.getDescripcion());
                i.putExtra("imagen_firebase", tienda.getImagen_firebase());
                i.putExtra("direccion", tienda.getDireccion());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

        });
    }

    @Override
    public int getItemCount() {
        return tiendas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //HACE REFERENCIA A LO UTILIZADO EN EL LAYOUT LIST_AVISO
        ImageView imagen;
        TextView nombre, Descripcion;
        RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            Descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            layout = (RelativeLayout) itemView.findViewById(R.id.layout);

        }


    }
}
