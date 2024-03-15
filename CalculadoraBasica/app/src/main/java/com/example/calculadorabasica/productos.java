package com.example.calculadorabasica;

public class productos {
    String idTienda;
    String foto;
    String codigo;
    String descripcion;
    String marca;
    String presentacion;
    String precio;

    public productos(String idTienda,String foto, String codigo,String descripcion,String marca, String presentacion,String precio){
        this.idTienda=idTienda;
        this.foto=foto;
        this.codigo=codigo;
        this.descripcion=descripcion;
        this.marca = marca;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
