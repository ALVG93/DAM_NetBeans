package com.mycompany.antonioluis_villalba_ad1_e2.modelo;

public class Cliente {
    private String nombreCompleto;
    private int anioNacimiento;
    private String provincia;
    private boolean premium;
    private double totalCompras;

    public Cliente() {
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }
}
