package com.example.desamparados.Clases;

public class Aviso {

    //FALTA AGREGAR VARIABLES

    private String nombre,descripcion;
    private int imagen, tipo; //ES DE TIPO ENTERO POR QUE SE LE SACA EL ID QUE RESIBE EN LA CARPETA DRAWABLE

    public Aviso(String nombre,String descripcion,int imagen){
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
