package com.mycompany.tarea01_alvg_2026.modelo.enums;

import java.util.ArrayList;

public enum Servicio {
    NUEVO(1, "Nuevo"),
    REACONDICIONADO(2,  "Reacondicionado"),
    OFERTA(3,  "Oferta");
    
    private int idServicio;
    private String nombreServicio;

    private Servicio(int idServicio, String nombreServicio) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }
    
    public static Servicio nombreServicioToEnum(String nombreServicio){
        for (Servicio servicio : Servicio.values()) {
            if (servicio.getNombreServicio().contains(nombreServicio)) {
                return servicio;
            }
        }
        
        return null;
    }
    
    public static String[] toArrayNombresServicio(){
        ArrayList<String> nombresServicio = new ArrayList<String>();
        String[] nombresServicioArray = {};
        
        for (Servicio servicio : Servicio.values()) {
            nombresServicio.add(servicio.getNombreServicio());
        }
        
        return nombresServicio.toArray(nombresServicioArray);
    }
}
