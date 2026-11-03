package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaBlanca;

public class ArticuloBlanco extends Articulo {
    private GamaBlanca gama;

    public ArticuloBlanco() {
    }

    public GamaBlanca getGama() {
        return gama;
    }

    public void setGama(GamaBlanca gama) {
        this.gama = gama;
    }

    @Override
    public String toString() {
        return "--- DATOS DEL ARTÍCULO CAPTURADO ---\n" + 
                "ArticuloBlanco{" + "gama=" + gama + '}' +
                super.toString();
    }
}
