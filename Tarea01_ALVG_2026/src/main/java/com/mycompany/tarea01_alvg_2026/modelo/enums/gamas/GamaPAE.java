package com.mycompany.tarea01_alvg_2026.modelo.enums.gamas;

import java.util.ArrayList;

public enum GamaPAE {
    CAFETERAS(1, "Cafeteras"),
    TOSTADORAS(2,  "Tostadoras"),
    BATIDORAS(3,  "Batidoras"),
    ROBOTS_DE_COCINA(4,  "Robots de cocina"),
    FREIDORAS(5,  "Freidoras"),
    MICROONDAS(6,  "Microondas"),
    PLANCHAS(7,  "Planchas"),
    ASPIRADORAS(8,  "Aspiradoras"),
    SECADORES_DE_PELO(9,  "Secadores de pelo"),
    AFEITADORAS(10,  "Afeitadoras");
    
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
