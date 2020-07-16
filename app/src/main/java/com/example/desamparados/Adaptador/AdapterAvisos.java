package com.example.desamparados.Adaptador;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.desamparados.Activity.DetalleAvisos;
import com.example.desamparados.Clases.Aviso;
import com.example.desamparados.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;



public class AdapterAvisos extends RecyclerView.Adapter<AdapterAvisos.ViewHolder> implements View.OnClickListener {

    private ArrayList<Aviso> listaAvisos;
    private View.OnClickListener click;
    private Context context;


    public AdapterAvisos(Context context, ArrayList<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
        this.context = context;

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
        final Aviso aviso  = listaAvisos.get(position);
        holder.nombre.setText(listaAvisos.get(position).getNombre());
        holder.Descripcion.setText(listaAvisos.get(position).getDescripcion());
        Picasso.get().load(listaAvisos.get(position).getImage_firebase()).into(holder.imagen);


        holder.layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetalleAvisos.class);
                i.putExtra("nombre", aviso.getNombre());
                i.putExtra("descripcion", aviso.getDescripcion());
                i.putExtra("imagen_firebase", aviso.getImage_firebase());
                i.putExtra("direccion", aviso.getDireccion());
                i.putExtra("estado", String.valueOf(aviso.getEstado()));
                i.putExtra("tipo_aviso", aviso.getTipoAviso().getNombre());
                i.putExtra("tipo_mascota", aviso.getTipoMascota().getNombre());


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

        });

    }

    @Override
    public int getItemCount() {
        //TOMA EL TAMAÑO DE LA LISTA
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
        //HACE REFERENCIA A LO UTILIZADO EN EL LAYOUT
        ImageView imagen;
        TextView nombre, Descripcion;
        LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            imagen = (ImageView) itemView.findViewById(R.id.imageView6);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            Descripcion = (TextView) itemView.findViewById(R.id.descripcion);

            layout = itemView.findViewById(R.id.layout);
        }

    }
}
