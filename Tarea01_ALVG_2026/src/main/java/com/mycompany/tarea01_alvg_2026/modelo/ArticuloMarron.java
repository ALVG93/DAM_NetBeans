package com.mycompany.tarea01_alvg_2026.modelo;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaMarron;

public class ArticuloMarron extends Articulo {
    private GamaMarron tipo;

    public ArticuloMarron() {
    }

    public ArticuloMarron(GamaMarron tipo) {
        this.tipo = tipo;
    }

    public GamaMarron getTipo() {
        return this.tipo;
    }

    public void setTipo(GamaMarron tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return super.toString(this.tipo.getNombreTipo());
    }
}
