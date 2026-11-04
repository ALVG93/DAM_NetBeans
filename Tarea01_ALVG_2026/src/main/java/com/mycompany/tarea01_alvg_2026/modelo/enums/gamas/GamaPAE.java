package com.mycompany.tarea01_alvg_2026.modelo.enums.gamas;

import java.util.ArrayList;

public enum GamaPAE {
    MICROONDAS(1,  "Microondas"),
    CAFETERA(2, "Cafetera"),
    TOSTADORA(3,  "Tostadora"),
    BATIDORA(4,  "Batidora"),
    ROBOT_DE_COCINA(5,  "Robot de cocina"),
    FREIDORA_DE_AIRE(6,  "Freidora de aire"),
    PLANCHA(7,  "Plancha"),
    ASPIRADORA(8,  "Aspiradora"),
    SECADOR_DE_PELO(9,  "Secador de pelo"),
    AFEITADORA(10,  "Afeitadora");
    
    private int idGama;
    private String nombreGama;

    private GamaPAE(int idGama, String nombreGama) {
        this.idGama = idGama;
        this.nombreGama = nombreGama;
    }

    public int getIdGama() {
        return idGama;
    }

    public String getNombreGama() {
        return nombreGama;
    }
    
    public static GamaPAE nombreGamaToEnum(String nombreGama){
        for (GamaPAE gama : GamaPAE.values()) {
            if (gama.getNombreGama().contains(nombreGama)) {
                return gama;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresGama(){
        ArrayList<String> nombresGama = new ArrayList<String>();
        String[] nombresGamaArray = {};
        
        for (GamaPAE gama : GamaPAE.values()) {
            nombresGama.add(gama.getNombreGama());
        }
        
        return nombresGama.toArray(nombresGamaArray);
    }
}
