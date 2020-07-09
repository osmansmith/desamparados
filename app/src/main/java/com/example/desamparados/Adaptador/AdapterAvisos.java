package com.example.desamparados.Adaptador;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
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


import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class AdapterAvisos extends RecyclerView.Adapter<AdapterAvisos.ViewHolder> implements View.OnClickListener {

    private ArrayList<Aviso> listaAvisos;
    private View.OnClickListener click;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance("gs://desamparados-4e547.appspot.com");
    StorageReference storageRef = storage.getReference();

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

       /* storageRef.child("users/me/profile.png").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Use the bytes to display the image
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
*/

        /*db.collection("aviso").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        holder.nombre.setText(document.getString("nombre"));
                        holder.Descripcion.setText(document.getString("descripcion"));
                       // holder.imagen.setImageResource(storageRef.getParent().child("earth.jpg"));
                        storageRef.child(document.getString("img")).getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                // Use the bytes to display the image
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                holder.imagen.setImageBitmap(bmp);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                               // Toast.makeText( "No Such file or Path found!!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());

                }
            }
        });

*/
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
