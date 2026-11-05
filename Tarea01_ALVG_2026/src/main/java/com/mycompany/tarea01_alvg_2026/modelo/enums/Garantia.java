package com.mycompany.tarea01_alvg_2026.modelo.enums;

import java.util.ArrayList;

public enum Garantia {
    UN_ANIO(1, "1 año"),
    TRES_ANIOS(2,  "3 años"),
    CINCO_ANIOS(3,  "5 años");
    
    private int idGarantia;
    private String nombreGarantia;

    private Garantia(int idGarantia, String nombreGarantia) {
        this.idGarantia = idGarantia;
        this.nombreGarantia = nombreGarantia;
    }

    public int getIdGarantia() {
        return idGarantia;
    }

    public String getNombreGarantia() {
        return nombreGarantia;
    }
    
    public static Garantia nombreGarantiaToEnum(String nombreGarantia){
        for (Garantia garantia : Garantia.values()) {
            if (garantia.getNombreGarantia().contains(nombreGarantia)) {
                return garantia;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresGarantia(){
        ArrayList<String> nombresGarantia = new ArrayList<String>();
        String[] nombresGarantiaArray = {};
        
        for (Garantia garantia : Garantia.values()) {
            nombresGarantia.add(garantia.getNombreGarantia());
        }
        
        return nombresGarantia.toArray(nombresGarantiaArray);
    }
}
