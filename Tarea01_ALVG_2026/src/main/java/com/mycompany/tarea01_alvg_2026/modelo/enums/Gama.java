package com.mycompany.tarea01_alvg_2026.modelo.enums;

import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaBlanca;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaMarron;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaPAE;
import java.util.ArrayList;

public enum Gama {
    BLANCA(1, "Blanca"),
    MARRON(2,  "Marrón"),
    PAE(3,  "PAE");
    
    private int idGama;
    private String nombreGama;

    private Gama(int idGama, String nombreGama) {
        this.idGama = idGama;
        this.nombreGama = nombreGama;
    }

    public int getIdGama() {
        return idGama;
    }

    public String getNombreGama() {
        return nombreGama;
    }
    
    public static Gama nombreGamaToEnum(String nombreGama){
        for (Gama gama : Gama.values()) {
            if (gama.getNombreGama().contains(nombreGama)) {
                return gama;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresGama(){
        ArrayList<String> nombresGama = new ArrayList<String>();
        String[] nombresGamaArray = {};
        
        for (Gama gama : Gama.values()) {
            nombresGama.add(gama.getNombreGama());
        }
        
        return nombresGama.toArray(nombresGamaArray);
    }
    
    public static String[] obtenerNombresTiposArticuloGama(String nombreGama) {
        Gama gama = nombreGamaToEnum(nombreGama);
        
        if(gama == null) {
            return null;
        }
        
        switch (gama) {
            case BLANCA:
                return GamaBlanca.toArrayNombresTipo();
            case MARRON:
                return GamaMarron.toArrayNombresTipo();
            case PAE:
                return GamaPAE.toArrayNombresTipo();
            default:
                return null;
        }
    }
}
