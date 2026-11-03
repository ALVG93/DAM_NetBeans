package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaPAE;

public class ArticuloPAE extends Articulo {
    private GamaPAE gama;

    public ArticuloPAE() {
    }

    public GamaPAE getGama() {
        return gama;
    }

    public void setGama(GamaPAE gama) {
        this.gama = gama;
    }
    
    @Override
    public String toString() {
        return "--- DATOS DEL ARTÍCULO CAPTURADO ---\n" + 
                "ArticuloBlanco{" + "gama=" + gama + '}' +
                super.toString();
    }
}
