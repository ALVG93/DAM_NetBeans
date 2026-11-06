package com.mycompany.tarea01_alvg_2026.modelo.enums;

import java.util.ArrayList;

public enum Servicio {
    RECOGIDA_EN_TIENDA(1, "Recogida en tienda"),
    ENTREGA_A_DOMICILIO(2,  "Entrega a domicilio"),
    INSTALACION_PUESTA_EN_MARCHA(3,  "Instalación y puesta en marcha"),
    RETIRADA_APARATO_USADO(3,  "Retirada del aparato usado (RAEE)");
    
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
