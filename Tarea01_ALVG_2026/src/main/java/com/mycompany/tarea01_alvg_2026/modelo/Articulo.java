package com.mycompany.tarea01_alvg_2026.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Articulo {
    private String codigo;
    private String denominacion;
    private String tipo;
    private String marca;
    private double precio;
    private int stock;
    private String claseEnergetica;
    private Date fechaEntrada;
    private String garantia;
    private String estado;
    private ArrayList<String> servicios;
    private String caracteristicas;

    public Articulo() {
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getClaseEnergetica() {
        return claseEnergetica;
    }

    public void setClaseEnergetica(String claseEnergetica) {
        this.claseEnergetica = claseEnergetica;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<String> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<String> servicios) {
        this.servicios = servicios;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        return "Código: " + codigo + "\n" +
               "Denominación: " + denominacion + "\n" +
               "Tipo de artículo: " + tipo + "\n" +
               "Marca: " + marca + "\n" +
               "Precio unitario: " + String.format("%.2f", precio) + " €\n" +
               "Stock disponible: " + stock + "\n" +
               "Clase energética: " + claseEnergetica + "\n" +
               "Fecha de entrada: " + sdf.format(fechaEntrada) + "\n" +
               "Garantía: " + garantia + "\n" +
               "Estado: " + estado + "\n" +
               "Servicios: " + String.join(", ", servicios) + "\n" +
               "Características: " + caracteristicas + "\n";
    }
}
