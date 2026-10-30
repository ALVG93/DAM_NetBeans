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
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorClientes {
    private static final String NOMBRE_FICHERO = "clientes.dat";
    
    private InterfazPrincipal interfazPrincipal;

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
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(NOMBRE_FICHERO, true));
            
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
        ModalMostrarClientes modalMostrarClientes = new ModalMostrarClientes(
                this.interfazPrincipal, this);
        
        modalMostrarClientes.setVisible(true);
    }
   
    public DefaultTableModel cargarClientes() {
        String[] columnas = {"Nombre Completo", "Año Nacimiento", "Provincia", "¿Premium?", "Total Compras"};
                
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        File file = new File(NOMBRE_FICHERO);
        
        if (!file.exists() || file.length() == 0) {
            mostrarInformacion("El fichero no existe o está vacío. Registre clientes primero.");
            return null;
        }

        int contador = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            while (true) {
                String nombre = dis.readUTF();
                int anio = dis.readInt();
                String provincia = dis.readUTF();
                boolean premium = dis.readBoolean();
                double compras = dis.readDouble();

                contador++;
                System.out.printf("[%d] Nombre: %s | Año: %d | Provincia: %s | Premium: %b | Total: %.2f€\n",
                        contador, nombre, anio, provincia, premium, compras);
            }
        } catch (EOFException e) {
            // El fin de fichero es la condición esperada para terminar el bucle de lectura secuencial.
            System.out.println("[INFO] Fin de lectura. Total de clientes leídos: " + contador);
        } catch (IOException e) {
            System.out.println("[ERROR] Error al leer el fichero: " + e.getMessage());
        }
    }
    
    public void mostrarMayorComprador() {
        System.out.println("\n--- Cliente con Mayor Volumen de Compras ---");
        File file = new File(NOMBRE_FICHERO);
        
        if (!file.exists() || file.length() == 0) {
            System.out.println("[INFO] No hay datos disponibles para calcular el mayor comprador.");
            return;
        }

        String maxNombre = "";
        double maxCompras = -1.0; // Inicializador menor al mínimo posible (0.0)

        try (DataInputStream dis = new DataInputStream(new FileInputStream(NOMBRE_FICHERO))) {
            while (true) {
                String nombre = dis.readUTF();
                int anio = dis.readInt();
                String provincia = dis.readUTF();
                boolean premium = dis.readBoolean();
                double compras = dis.readDouble();

                if (compras > maxCompras) {
                    maxCompras = compras;
                    maxNombre = nombre;
                }
            }
        } catch (EOFException e) {
            // Fin del archivo alcanzado de forma correcta
            if (maxCompras >= 0) {
                System.out.println("=================================================");
                System.out.printf(" CLIENTE: %s\n", maxNombre);
                System.out.printf(" IMPORTE TOTAL COMPRAS: %.2f€\n", maxCompras);
                System.out.println("=================================================");
            } else {
                System.out.println("[INFO] No se procesaron registros válidos.");
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Error al evaluar el fichero: " + e.getMessage());
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
}
