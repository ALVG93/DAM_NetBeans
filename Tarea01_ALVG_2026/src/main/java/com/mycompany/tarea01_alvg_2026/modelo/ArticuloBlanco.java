package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaBlanca;

public class ArticuloBlanco extends Articulo {
    private GamaBlanca tipo;

    public ArticuloBlanco() {
    }

    public GamaBlanca getTipo() {
        return this.tipo;
    }

    public void setTipo(GamaBlanca tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "--- DATOS DEL ARTÍCULO CAPTURADO ---\n" + 
                "tipo: " + this.tipo.getNombreTipo() + '\n' +
                super.toString();
    }
}
