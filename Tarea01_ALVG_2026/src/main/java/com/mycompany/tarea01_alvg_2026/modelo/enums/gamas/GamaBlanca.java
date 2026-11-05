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
    
    private int idTipo;
    private String nombreTipo;

    private GamaBlanca(int idTipo, String nombreTipo) {
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }
    
    public static GamaBlanca nombreTipoToEnum(String nombreTipo){
        for (GamaBlanca tipo : GamaBlanca.values()) {
            if (tipo.getNombreTipo().contains(nombreTipo)) {
                return tipo;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresTipo(){
        ArrayList<String> nombresTipo = new ArrayList<String>();
        String[] nombresTipoArray = {};
        
        for (GamaBlanca tipo : GamaBlanca.values()) {
            nombresTipo.add(tipo.getNombreTipo());
        }
        
        return nombresTipo.toArray(nombresTipoArray);
    }
}
