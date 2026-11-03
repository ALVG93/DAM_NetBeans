package com.mycompany.tarea01_alvg_2026.modelo.enums.gamas;

import java.util.ArrayList;

public enum GamaMarron {
    TELEVSORES(1, "Televisores"),
    BARRAS_DE_SONIDO(2,  "Barras de sonido"),
    EQUIPOS_DE_MUSICA(3,  "Equipos de música"),
    HOME_CINEMAS(4,  "Home cinemas"),
    PROYECTORES(5,  "Proyectores");
    
    private int idGama;
    private String nombreGama;

    private GamaMarron(int idGama, String nombreGama) {
        this.idGama = idGama;
        this.nombreGama = nombreGama;
    }

    public int getIdGama() {
        return idGama;
    }

    public String getNombreGama() {
        return nombreGama;
    }
    
    public static GamaMarron nombreGamaToEnum(String nombreGama){
        for (GamaMarron gama : GamaMarron.values()) {
            if (gama.getNombreGama().contains(nombreGama)) {
                return gama;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresGama(){
        ArrayList<String> nombresGama = new ArrayList<String>();
        String[] nombresGamaArray = {};
        
        for (GamaMarron gama : GamaMarron.values()) {
            nombresGama.add(gama.getNombreGama());
        }
        
        return nombresGama.toArray(nombresGamaArray);
    }
}
