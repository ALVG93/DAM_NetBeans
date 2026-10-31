package com.mycompany.antonioluis_villalba_ad1_e1.controlador;

import com.mycompany.antonioluis_villalba_ad1_e1.modelo.Cliente;
import com.mycompany.antonioluis_villalba_ad1_e1.vista.InterfazPrincipal;
import com.mycompany.antonioluis_villalba_ad1_e1.vista.ModalAltaCliente;
import com.mycompany.antonioluis_villalba_ad1_e1.vista.ModalMostrarClientes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ControladorClientes {
    private static final String NOMBRE_FICHERO = "clientes.dat";
    
    private InterfazPrincipal interfazPrincipal;
    private ModalMostrarClientes modalMostrarClientes;

    public void gestionarClientes() {
        this.interfazPrincipal = new InterfazPrincipal(this);
        this.interfazPrincipal.setVisible(true);
    }
    
    public void solicitarDatosNuevoCliente() {
        ModalAltaCliente modalAltaCliente = new ModalAltaCliente(
                this.interfazPrincipal, this, new Cliente());
        
        modalAltaCliente.setVisible(true);
    }
    
    public boolean nombreClienteValido(String nombreCompleto){
        if (nombreCompleto.isEmpty()) {
            mostrarError("El nombre no puede estar vacío.");
            return false;
        }
        
        return true;
    }
    
    public boolean anioNacimientoValido(String anioNacimiento) {
        if (anioNacimiento.isEmpty()) {
            mostrarError("El año de nacimiento no puede estar vacía.");
            return false;
        }
        
        int anio = 0;
        
        try {
            anio = Integer.parseInt(anioNacimiento);
        } catch (NumberFormatException e) {
            mostrarError("El año de nacimiento debe ser un número entero.");
            return false;
        }
        
        if (anio < 1900 || anio > 2026) {
            mostrarError("El año de nacimiento debe ser mayor que 1900 y menor que 2026.");
            return false;
        }
        
        return true;
    }
    
    public boolean provinciaValida(String provincia) {
        if (provincia.isEmpty()) {
            mostrarError("La provincia no puede estar vacío.");
            return false;
        }
        
        return true;
    }
    
    public boolean totalComprasValido(String totalCompras) {
        if (totalCompras.isEmpty()) {
            mostrarError("El total de compras no puede estar vacío.");
            return false;
        }
        
        double total = 0;
        
        try {
            total = Double.parseDouble(totalCompras);
        } catch (NumberFormatException e) {
            mostrarError("El total de compras debe ser un número.");
            return false;
        }
        
        if (total < 0) {
            mostrarError("El total de compras debe ser un número mayor que 0.");
            return false;
        }
        
        return true;
    }
    
    public boolean altaCliente(Cliente nuevoCliente) {        
        try {
            DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream(NOMBRE_FICHERO, true));
            
            dos.writeUTF(nuevoCliente.getNombreCompleto());
            dos.writeInt(nuevoCliente.getAnioNacimiento());
            dos.writeUTF(nuevoCliente.getProvincia());
            dos.writeBoolean(nuevoCliente.isPremium());
            dos.writeDouble(nuevoCliente.getTotalCompras());

            mostrarInformacion("Cliente guardado correctamente en el fichero.");
        } catch (IOException e) {
            mostrarError("Error al escribir en el fichero de clientes.");
        }
        
        return true;
    }
    
    public void mostrarClientes() {
        this.modalMostrarClientes = new ModalMostrarClientes(
                this.interfazPrincipal, this);
        
        cargarClientes();
        
        this.modalMostrarClientes.recargarTabla();
        
        this.modalMostrarClientes.setVisible(true);
    }
   
    private void cargarClientes() {
        File fichero = cargarFichero();

        if(fichero == null) {
            return;
        } 
        
        Cliente clienteLeido = new Cliente();
        
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new FileInputStream(NOMBRE_FICHERO));
                    
            while (true) {
                leerCliente(dataInputStream, clienteLeido);
                
                Object[] fila = generarFilaTabla(clienteLeido);
                
                this.modalMostrarClientes.agregarFilaAtabla(fila);
            }
        } catch (EOFException e) {
            mostrarInformacion("Fichero leido correctamente.");
        } catch (IOException e) {
            mostrarError("Error al leer el fichero.");
        }
    }
    
    private Object[] generarFilaTabla(Cliente cliente) {
        Object[] fila = {
                    cliente.getNombreCompleto(),
                    cliente.getAnioNacimiento(),
                    cliente.getProvincia(),
                    cliente.isPremium() ? "Sí" : "No",
                    String.format("%.2f €", cliente.getTotalCompras())
                };
        
        return fila;
    }
    
    public void mostrarMayorComprador() {
        File fichero = cargarFichero();

        if(fichero == null) {
            return;
        }

        Cliente clienteLeido = new Cliente();
        String nombreClienteMayorCompras = "";
        double cantidadComprarMasAlta = -1.0;

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            while (true) {
                leerCliente(dataInputStream, clienteLeido);
                
                if (clienteLeido.getTotalCompras() > cantidadComprarMasAlta) {
                    nombreClienteMayorCompras = clienteLeido.getNombreCompleto();
                    cantidadComprarMasAlta = clienteLeido.getTotalCompras();
                }
            }
        } catch (EOFException e) {
            // Fin del archivo alcanzado de forma correcta
            if (cantidadComprarMasAlta >= 0) {
                String cliente = " Cliente: " + nombreClienteMayorCompras + "\n";
                cliente += " Importe total comprado: " + String.format("%.2f €", cantidadComprarMasAlta) + "\n";
                
                mostrarInformacion(cliente);
            } else {                        
                mostrarInformacion("No se procesaron registros válidos.");
            }
        } catch (IOException e) {
            mostrarError("Error al evaluar el fichero.");
        }
    }
    
    private void mostrarError(String mensajeError) {
        JOptionPane.showMessageDialog(
                this.interfazPrincipal, 
                mensajeError, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarInformacion(String mensaje) {
        JOptionPane.showMessageDialog(
                this.interfazPrincipal, 
                mensaje, 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    private File cargarFichero() {
        File fichero = new File(NOMBRE_FICHERO);
        
        if (!fichero.exists() || fichero.length() == 0) {
            mostrarInformacion("El fichero no existe o está vacío. Registre clientes primero.");
            return null;
        }
        
        return fichero;
    }

    private void leerCliente(DataInputStream dis, Cliente clienteLeido) throws IOException {
        clienteLeido.setNombreCompleto(dis.readUTF());
        clienteLeido.setAnioNacimiento(dis.readInt());
        clienteLeido.setProvincia(dis.readUTF());
        clienteLeido.setPremium(dis.readBoolean());
        clienteLeido.setTotalCompras(dis.readDouble());
    }
}
