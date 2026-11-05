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
    
    private int idTipo;
    private String nombreTipo;

    private GamaPAE(int idTipo, String nombreTipo) {
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }
    
    public static GamaPAE nombreTipoToEnum(String nombreTipo){
        for (GamaPAE tipo : GamaPAE.values()) {
            if (tipo.getNombreTipo().contains(nombreTipo)) {
                return tipo;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresTipo(){
        ArrayList<String> nombresTipo = new ArrayList<String>();
        String[] nombresTipoArray = {};
        
        for (GamaPAE tipo : GamaPAE.values()) {
            nombresTipo.add(tipo.getNombreTipo());
        }
        
        return nombresTipo.toArray(nombresTipoArray);
    }
}
