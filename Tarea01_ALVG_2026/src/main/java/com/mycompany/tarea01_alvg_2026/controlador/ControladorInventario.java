package com.mycompany.tarea01_alvg_2026.controlador;

import com.mycompany.tarea01_alvg_2026.modelo.Articulo;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Gama;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaBlanca;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaMarron;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaPAE;
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
    
    public boolean codigoValido(String codigo) {
        if (codigo.isEmpty()) {
            mostrarError("Se debe registrar un código.");
            return false;
        } else if(codigo.length() > 8) {
            mostrarError("El código no puede tener más de 8 caracteres.");
            return false;
        }
        
        return true;
    }
    
    public boolean denominacionValida(String denominacion) {
        if (denominacion.isEmpty()) {
            mostrarError("Se debe registrar una denominación.");
            return false;
        } else if(denominacion.length() > 8) {
            mostrarError("La denominacion no puede tener más de 25 caracteres.");
            return false;
        }
        
        return true;
    }
    
    public boolean gamaValida(String gama) {
        if(Gama.nombreGamaToEnum(gama) == null) {
            mostrarError("Se debe seleccionar una gama.");
            return false;
        }
        
        return true;
    }
    
    public boolean tipoValido(String tipo) {
        GamaBlanca gamaBlanca = GamaBlanca.nombreTipoToEnum(tipo);
        GamaMarron gamaMarron = GamaMarron.nombreTipoToEnum(tipo);
        GamaPAE gamaPAE = GamaPAE.nombreTipoToEnum(tipo);
        
        if(gamaBlanca == null && gamaMarron == null && gamaPAE == null ) {
            mostrarError("Se debe seleccionar un tipo.");
            return false;
        }
        
        return true;
    }
    
    public boolean marcaValida(String marca) {
        if (marca.isEmpty()) {
            mostrarError("Se debe registrar una marca.");
            return false;
        } else if(marca.length() > 8) {
            mostrarError("La marca no puede tener más de 30 caracteres.");
            return false;
        }
        
        return true;
    }
    
    public boolean precioValido(String precioString) {
        if (precioString.isEmpty()) {
            mostrarError("El precio del artículo no puede estar vacío.");
            return false;
        }
        
        double precio = 0;
        
        try {
            precio = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            mostrarError("El precio del artículo debe ser un número.");
            return false;
        }
        
        if (precio < 0) {
            mostrarError("El precio del artículo debe ser un número mayor que 0 €.");
            return false;
        } else if (precio > Articulo.PRECIO_MAXIMO) {
            mostrarError("El precio del artículo no puede ser mayor a 10000 €.");
            return false;
        }
        
        return true;
    }
    
    public void guardarArticulo() {
        
    }
    
    private void mostrarError(String mensajeError) {
        JOptionPane.showMessageDialog(
                this.interfazPrincipal, 
                mensajeError, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
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
