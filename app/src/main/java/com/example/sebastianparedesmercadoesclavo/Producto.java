package com.example.sebastianparedesmercadoesclavo;

public class Producto {

    private Integer imagen;
    private String nombre;
    private String precio;

    public Producto(Integer imagen, String nombre, String precio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
