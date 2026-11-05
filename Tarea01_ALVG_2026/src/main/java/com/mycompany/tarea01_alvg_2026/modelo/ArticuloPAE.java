package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaPAE;

public class ArticuloPAE extends Articulo {
    private GamaPAE tipo;

    public ArticuloPAE() {
    }

    public GamaPAE getTipo() {
        return this.tipo;
    }

    public void setTipo(GamaPAE tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "--- DATOS DEL ARTÍCULO CAPTURADO ---\n" + 
                "tipo: " + this.tipo.getNombreTipo() + '\n' +
                super.toString();
    }
}
