package com.mycompany.tarea01_alvg_2026.controlador;

import com.mycompany.tarea01_alvg_2026.modelo.Articulo;
import com.mycompany.tarea01_alvg_2026.modelo.enums.EficienciaEnergetica;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Estado;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Gama;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Garantia;
import com.mycompany.tarea01_alvg_2026.modelo.enums.Servicio;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaBlanca;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaMarron;
import com.mycompany.tarea01_alvg_2026.modelo.enums.gamas.GamaPAE;
import com.mycompany.tarea01_alvg_2026.vista.InterfazPrincipal;
import com.mycompany.tarea01_alvg_2026.vista.ModalAltaArticulos;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ControladorInventario {
    private InterfazPrincipal interfazPrincipal;
    private ModalAltaArticulos modalAltaArticulos;

    public void gestionarInventario() {
        this.interfazPrincipal = new InterfazPrincipal(this);
        this.interfazPrincipal.setVisible(true);
    }
    
    public void modalAltaArticulo() {
        this.modalAltaArticulos = new ModalAltaArticulos(
                this.interfazPrincipal, this);
        
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
        } else if(denominacion.length() > 25) {
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
            precio = Double.parseDouble(precioString.replaceAll(",", "."));
        } catch (NumberFormatException e) {
            mostrarError("El precio del artículo debe ser un número.");
            return false;
        }
        
        int posicionPunto = precioString.replaceAll(",", ".").indexOf(".");
        
        if(posicionPunto != -1) {
            int cantidadDecimales = precioString.length() - posicionPunto - 1;

            if (cantidadDecimales > 2) {
                mostrarError("El precio no puede tener más de 2 decimales.");
                return false;
            }
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
    
    public boolean stockValido(String stockString) {
        if (stockString.isEmpty()) {
            mostrarError("El stock del artículo no puede estar vacío.");
            return false;
        }
        
        double stock = 0;
        
        try {
            stock = Integer.parseInt(stockString);
        } catch (NumberFormatException e) {
            mostrarError("El stock del artículo debe ser un número, y sin decimales.");
            return false;
        }
        
        if (stock < 0) {
            mostrarError("El stock del artículo no puede ser menor a 0.");
            return false;
        }
        
        return true;
    }
    
    public boolean claseEnergeticaValida(String claseEnergetica) {
        if(EficienciaEnergetica.nombreEficienciaEnergeticaToEnum(claseEnergetica) == null) {
            mostrarError("Se debe seleccionar una clase de eficiencia energética.");
            return false;
        }
        
        return true;
    }
    
    public boolean fechaEntradaValida(Date fechaEntrada) {
        if (fechaEntrada == null) {
            mostrarError("La fecha de entrada no puede quedar vacia.");
            return false;
        }

        LocalDate fechaElegida = fechaEntrada.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        LocalDate hoy = LocalDate.now();

        if (fechaElegida.isAfter(hoy)) {
            mostrarError("La fecha de entrada no puede ser posterior hoy.");
            return false;
        }
        
        return true;
    }
    
    public boolean garantiaValida(Garantia garantia) {
        if(garantia == null) {
            mostrarError("Se debe seleccionar la garantía del artículo.");
            return false;
        }
        
        return true;
    }
    
    public boolean estadoValida(Estado estado) {
        if(estado == null) {
            mostrarError("Se debe seleccionar un estado del artículo.");
            return false;
        }
        
        return true;
    }
    
    public boolean serviciosValidos(ArrayList<Servicio> servicios) {
        if(servicios.isEmpty()) {
            mostrarError("Se debe seleccionar al menos un servicio que asociar al artículo.");
            return false;
        }
        
        return true;
    }
    
    public boolean descripcionValida(String descripcion) {
        if(descripcion.isEmpty()) {
            mostrarError("Se cumplimentar una descripción del artículo.");
            return false;
        }
        
        return true;
    }
    
    public void mostrarArticuloLeido(Articulo articulo) {
        JTextArea jTextAreaArticulo = new JTextArea(articulo.toString());
        jTextAreaArticulo.setEditable(false);

        JScrollPane jScrollPaneArticulo = new JScrollPane(jTextAreaArticulo);
        jScrollPaneArticulo.setPreferredSize(new Dimension(500, 300));

        Object[] cuerpoMensaje = {
            "Datos capturados por el formulario. Al ser una maqueta, no se almacenan.\n\n",
            jScrollPaneArticulo
        };
        
        mostrarInformacion("Registro guardado.");
        
        JOptionPane.showMessageDialog(
                this.interfazPrincipal, 
                cuerpoMensaje, 
                "Datos recogidos", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean fechaEntradaModificada(Date fechaEntrada) {
        LocalDate fechaElegida = fechaEntrada.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        LocalDate hoy = LocalDate.now();

        if (fechaElegida.isEqual(hoy)) {
            return false;
        }
        
        return true;
    }
    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(
                this.interfazPrincipal, 
                mensaje, 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
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
    
    public void cerrarModalAltaArticulo(boolean algunDatoIntroducido) {
        if(!algunDatoIntroducido) {
            this.modalAltaArticulos.dispose();
        } else {
            if (preguntarSiSalir() == JOptionPane.YES_OPTION) {
                this.modalAltaArticulos.dispose();
            }
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
