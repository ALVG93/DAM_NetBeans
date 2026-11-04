package com.mycompany.tarea01_alvg_2026.modelo.enums;

import java.util.ArrayList;

public enum EficienciaEnergetica {
    A(1, "A"),
    B(2,  "B"),
    C(3,  "C"),
    D(4,  "D"),
    E(5,  "E"),
    F(6,  "F"),
    G(7,  "G"),
    NO_APLICA(8, "No aplica");
    
    private int idEficienciaEnergetica;
    private String nombreEficienciaEnergetica;

    private EficienciaEnergetica(int idEficienciaEnergetica, String nombreEficienciaEnergetica) {
        this.idEficienciaEnergetica = idEficienciaEnergetica;
        this.nombreEficienciaEnergetica = nombreEficienciaEnergetica;
    }

    public int getIdEficienciaEnergetica() {
        return idEficienciaEnergetica;
    }

    public String getNombreEficienciaEnergetica() {
        return nombreEficienciaEnergetica;
    }
    
    public static EficienciaEnergetica nombreEficienciaEnergeticaToEnum(String nombreEficienciaEnergetica){
        for (EficienciaEnergetica eficienciaEnergetica : EficienciaEnergetica.values()) {
            if (eficienciaEnergetica.getNombreEficienciaEnergetica().contains(nombreEficienciaEnergetica)) {
                return eficienciaEnergetica;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresEficienciaEnergetica(){
        ArrayList<String> nombresEficienciaEnergetica = new ArrayList<String>();
        String[] nombresEficienciaEnergeticaArray = {};
        
        for (EficienciaEnergetica eficienciaEnergetica : EficienciaEnergetica.values()) {
            nombresEficienciaEnergetica.add(eficienciaEnergetica.getNombreEficienciaEnergetica());
        }
        
        return nombresEficienciaEnergetica.toArray(nombresEficienciaEnergeticaArray);
    }
}
