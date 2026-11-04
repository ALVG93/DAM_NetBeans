package com.mycompany.tarea01_alvg_2026.modelo.enums.gamas;

import java.util.ArrayList;

public enum GamaBlanca {
    FRIGORIFICO(1, "Frigorífico"),
    CONGELADOR(2, "Congelador"),
    LAVADORA(3,  "Lavadora"),
    SECADORA(4,  "Secadora"),
    LAVAVAJILLAS(5,  "Lavavajillas"),
    HORNO(6,  "Horno"),
    PLACA_DE_COCCION(7,  "Placa de cocción"),
    CAMPANA_EXTRACTORA(8,  "Campana extractora"),
    AIRE_ACONDICIONADO(9,  "Aire acondicionado"),
    TERMO_ELECTRICO(10, "Termo eléctrico");
    
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
