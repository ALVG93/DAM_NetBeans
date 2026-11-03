package com.mycompany.tarea01_alvg_2026.controlador;

import com.mycompany.tarea01_alvg_2026.modelo.Articulo;
import com.mycompany.tarea01_alvg_2026.vista.InterfazPrincipal;
import com.mycompany.tarea01_alvg_2026.vista.ModalAltaArticulos;
import javax.swing.JOptionPane;

public class ControladorInventario {
    private InterfazPrincipal interfazPrincipal;
    private ModalAltaArticulos modalAltaArticulos;

    public void gestionarInventario() {
        this.interfazPrincipal = new InterfazPrincipal(this);
        this.interfazPrincipal.setVisible(true);
    }
    
    public void modalAltaArticulo() {
        this.modalAltaArticulos = new ModalAltaArticulos(
                this.interfazPrincipal, this, new Articulo());
        
        this.modalAltaArticulos.setVisible(true);
    }
    
    public void cerrarPrograma() {
        if (preguntarSiSalir() == JOptionPane.YES_OPTION) {
            this.interfazPrincipal.dispose();
        }
    }
    
    public void cerrarModalAltaArticulo() {
        if (preguntarSiSalir() == JOptionPane.YES_OPTION) {
            this.modalAltaArticulos.dispose();
        }
    }
    
    private int preguntarSiSalir() {
        int respuesta = JOptionPane.showConfirmDialog(
            this.interfazPrincipal,
            "¿Está seguro de salir?",
            "Confirmar salida",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );
        
        return respuesta;
    }
}
