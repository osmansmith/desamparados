package com.Desampara2.desamparados.Clases;

import java.io.Serializable;

public class Aviso implements Serializable {

    //FALTA AGREGAR VARIABLES
    private String id;//string porque desde firebase se crea un id de tipo string
    private String nombre;
    private String descripcion;
    private int imagen;
    private TipoAviso tipoAviso; //ES DE TIPO ENTERO POR QUE SE LE SACA EL ID QUE RESIBE EN LA CARPETA DRAWABLE
    private TipoMascota tipoMascota;
    private int estado; // 0 = eliminado - 1 = activo
    private double latitud;
    private double longitud;
    private String direccion;
    private String image_firebase;


    public Aviso() {
    }

    public Aviso(String id, String nombre, String descripcion, int imagen, TipoAviso tipoAviso, TipoMascota tipoMascota, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.tipoAviso = tipoAviso;
        this.tipoMascota = tipoMascota;
        this.estado = estado;

    }

    public Aviso(String nombre, String descripcion, int imagen){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public TipoAviso getTipoAviso() {
        return tipoAviso;
    }

    public void setTipoAviso(TipoAviso tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImage_firebase() {
        return image_firebase;
    }

    public void setImage_firebase(String image_firebase) {
        this.image_firebase = image_firebase;
    }
}
