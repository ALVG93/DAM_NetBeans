package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaMarron;

public class ArticuloMarron extends Articulo {
    private GamaMarron gama;

    public ArticuloMarron() {
    }

    public GamaMarron getGama() {
        return gama;
    }

    public void setGama(GamaMarron gama) {
        this.gama = gama;
    }
    
    @Override
    public String toString() {
        return "--- DATOS DEL ARTÍCULO CAPTURADO ---\n" + 
                "ArticuloBlanco{" + "gama=" + gama + '}' +
                super.toString();
    }
}
