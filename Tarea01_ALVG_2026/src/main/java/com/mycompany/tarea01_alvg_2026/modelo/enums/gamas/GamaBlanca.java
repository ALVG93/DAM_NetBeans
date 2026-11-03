package com.mycompany.tarea01_alvg_2026.modelo.enums.gamas;

import java.util.ArrayList;

public enum GamaBlanca {
    FRIGORIFICOS(1, "Frigorificos"),
    LAVADORAS(2,  "Lavadoras"),
    LAVAVAJILLAS(3,  "Lavavajillas"),
    HORNOS(4,  "Hornos"),
    PLACAS(5,  "Placas"),
    CAMPANAS(6,  "Campanas"),
    SECADORAS(7,  "Secadoras"),
    AIRES_ACONDICIONADO(8,  "Aires acondicionado");
    
    private int idGama;
    private String nombreGama;

    private GamaBlanca(int idGama, String nombreGama) {
        this.idGama = idGama;
        this.nombreGama = nombreGama;
    }

    public int getIdGama() {
        return idGama;
    }

    public String getNombreGama() {
        return nombreGama;
    }
    
    public static GamaBlanca nombreGamaToEnum(String nombreGama){
        for (GamaBlanca gama : GamaBlanca.values()) {
            if (gama.getNombreGama().contains(nombreGama)) {
                return gama;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresGama(){
        ArrayList<String> nombresGama = new ArrayList<String>();
        String[] nombresGamaArray = {};
        
        for (GamaBlanca gama : GamaBlanca.values()) {
            nombresGama.add(gama.getNombreGama());
        }
        
        return nombresGama.toArray(nombresGamaArray);
    }
}
