package com.mycompany.tarea01_alvg_2026.modelo.enums.gamas;

import java.util.ArrayList;

public enum GamaMarron {
    TELEVSOR(1, "Televisor"),
    BARRA_DE_SONIDO(2,  "Barra de sonido"),
    EQUIPO_DE_MUSICA(3,  "Equipo de música"),
    HOME_CINEMA(4,  "Home cinema"),
    PROYECTOR(5,  "Proyector"),
    REPRODUCTOR_MULTIMEDIA(6, "Reproductor multimedia");
    
    private int idTipo;
    private String nombreTipo;

    private GamaMarron(int idTipo, String nombreTipo) {
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }
    
    public static GamaMarron nombreTipoToEnum(String nombreTipo){
        for (GamaMarron tipo : GamaMarron.values()) {
            if (tipo.getNombreTipo().contains(nombreTipo)) {
                return tipo;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresTipo(){
        ArrayList<String> nombresTipo = new ArrayList<String>();
        String[] nombresTipoArray = {};
        
        for (GamaMarron tipo : GamaMarron.values()) {
            nombresTipo.add(tipo.getNombreTipo());
        }
        
        return nombresTipo.toArray(nombresTipoArray);
    }
}
