package com.Desampara2.desamparados.Adaptador;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.Desampara2.desamparados.Activity.DetalleAvisos;
import com.Desampara2.desamparados.Clases.Aviso;
import com.Desampara2.desamparados.DbHelper;
import com.Desampara2.desamparados.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;



public class AdapterAvisos extends RecyclerView.Adapter<AdapterAvisos.ViewHolder> implements View.OnClickListener {

    private ArrayList<Aviso> listaAvisos;
    private View.OnClickListener click;
    private Context context;
    private DbHelper helper;
    private SQLiteDatabase db;

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
        holder.tipoAviso.setText(listaAvisos.get(position).getTipoAviso().getNombre());
        holder.Descripcion.setText(listaAvisos.get(position).getDescripcion());
        Picasso.get().load(listaAvisos.get(position).getImage_firebase()).into(holder.imagen);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);

        if(account!=null) {
            holder.estrella.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //base de datos interna
                    helper = new DbHelper(context);
                    //estrella click listener
                    if (holder.estrella.getColorFilter() != null) {
                        holder.estrella.clearColorFilter();
                        //pub_id = h.hpub_id.getText().toString();
                        //aquí elimina
                        //Drawer dra = new Drawer();
                        //dra.deleteFavorites(h.hpub_id.getText().toString(), Singin.user.getUser_email());
                        //helper.eliminar();
                        Toast.makeText(context, "Favorito eliminado"/*+ pub_id*/, Toast.LENGTH_SHORT).show();



                    } else {
                        holder.estrella.setColorFilter(ContextCompat.getColor(context,
                                R.color.favorito_color));
                        //pub_id = h.hpub_id.getText().toString();
                        //aquí añade
                        //Drawer dra = new Drawer();
                        //dra.addFavorites(dra.createFavorite());
                       // helper.insertardatos();
                        Toast.makeText(context, "Favorito Añadido"/*+ pub_id*/, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

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
        RelativeLayout layout;
        ImageView estrella;
        TextView tipoAviso;
        public ViewHolder(View view) {
            super(view);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            Descripcion = (TextView) itemView.findViewById(R.id.descripcion);
            estrella = itemView.findViewById(R.id.favorito);
            tipoAviso = (TextView)  itemView.findViewById(R.id.tipo_aviso);
            layout = itemView.findViewById(R.id.layout);
        }

    }
}
