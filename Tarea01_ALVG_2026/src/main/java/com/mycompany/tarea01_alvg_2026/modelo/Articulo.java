package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.EficienciaEnergetica;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Estado;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Gama;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Garantia;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Servicio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Articulo {
    public static final double PRECIO_MAXIMO = 10000d;
    
    private String codigo;
    private String denominacion;
    private Gama gama;
    private String marca;
    private double precio;
    private int stock;
    private EficienciaEnergetica claseEnergetica;
    private Date fechaEntrada;
    private Garantia garantia;
    private Estado estado;
    private ArrayList<Servicio> servicios;
    private String caracteristicas;

    public Articulo() {
        
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return this.denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Gama getGama() {
        return this.gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public EficienciaEnergetica getClaseEnergetica() {
        return this.claseEnergetica;
    }

    public void setClaseEnergetica(EficienciaEnergetica claseEnergetica) {
        this.claseEnergetica = claseEnergetica;
    }

    public Date getFechaEntrada() {
        return this.fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Garantia getGarantia() {
        return this.garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<Servicio> getServicios() {
        return this.servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String servicios = "";
        
        for(Servicio servicio : this.servicios) {
            servicios += servicio.getNombreServicio() + ", ";
        }
        
        servicios = servicios.substring(0, servicios.length() - 2);
        
        return "Código: " + this.codigo + "\n" +
               "Denominación: " + this.denominacion + "\n" +
               "Gama: " + this.gama.getNombreGama() + "\n" +
               "Marca: " + this.marca + "\n" +
               "Precio unitario: " + String.format("%.2f", this.precio) + " €\n" +
               "Stock disponible: " + this.stock + "\n" +
               "Clase energética: " + this.claseEnergetica.getNombreEficienciaEnergetica() + "\n" +
               "Fecha de entrada: " + sdf.format(this.fechaEntrada) + "\n" +
               "Garantía: " + this.garantia.getNombreGarantia() + "\n" +
               "Estado: " + this.estado.getNombreEstado() + "\n" +
               "Servicios: " + servicios + "\n" +
               "Características: " + this.caracteristicas + "\n";
    }
}
