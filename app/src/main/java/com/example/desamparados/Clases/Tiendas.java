package com.example.desamparados.Clases;


public class Tiendas {

    //VARIABLES DE UNA EMPRESA YA SEA VETERINARIA O UN PETSHOP
    private String nombre,descripcion;
    private String latitud;
    private String longitud;
    private String direccion;
    private String imagen_firebase;
    private int imagen, tipo;//ES DE TIPO ENTERO POR QUE SE LE SACA EL ID QUE RESIBE EN LA CARPETA DRAWABLE

    public Tiendas(String nombre,String descripcion,int imagen){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion=descripcion;
    }

    public int getImagen() {
        return this.imagen;
    }

    public void setImagen(int imagen) {
        this.imagen= imagen;
    }

    public int getTipo() { return this.tipo; }

    public void settipo(int tipo) { this.tipo=tipo; }

    public String getImagen_firebase() {
        return imagen_firebase;
    }

    public void setImagen_firebase(String imagen_firebase) {
        this.imagen_firebase = imagen_firebase;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
