package com.example.desamparados.Clases;

import androidx.annotation.NonNull;

public class Tiendas {

    //VARIABLES DE UNA EMPRESA YA SEA VETERINARIA O UN PETSHOP
    private String nombre,descripcion;
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
}
