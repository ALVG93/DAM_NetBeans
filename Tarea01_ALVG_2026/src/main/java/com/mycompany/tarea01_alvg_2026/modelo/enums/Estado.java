package com.mycompany.tarea01_alvg_2026.modelo.enums;

import java.util.ArrayList;

public enum Estado {
    NUEVO(1, "Nuevo"),
    REACONDICIONADO(2,  "Reacondicionado"),
    OFERTA(3,  "Oferta");
    
    private int idEstado;
    private String nombreEstado;

    private Estado(int idEstado, String nombreEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }
    
    public static Estado nombreEstadoToEnum(String nombreEstado){
        for (Estado estado : Estado.values()) {
            if (estado.getNombreEstado().contains(nombreEstado)) {
                return estado;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresEstado(){
        ArrayList<String> nombresEstado = new ArrayList<String>();
        String[] nombresEstadoArray = {};
        
        for (Estado estado : Estado.values()) {
            nombresEstado.add(estado.getNombreEstado());
        }
        
        return nombresEstado.toArray(nombresEstadoArray);
    }
}
