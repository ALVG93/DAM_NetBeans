package com.mycompany.antonioluis_villalba_ad1_e1;

import com.mycompany.antonioluis_villalba_ad1_e1.controlador.ControladorClientes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AntonioLuis_Villalba_AD1_E1 {
    private static final String FILE_NAME = "clientes.dat";
    
    public static void main(String[] args) {
        ControladorClientes controladorClientes = new ControladorClientes();
        
        controladorClientes.gestionarClientes();
        
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Dar de alta un nuevo cliente");
            System.out.println("2. Mostrar todos los clientes");
            System.out.println("3. Mostrar el cliente con mayor volumen de compras");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        altaCliente(scanner);
                        break;
                    case 2:
                        mostrarClientes();
                        break;
                    case 3:
                        mostrarMayorComprador();
                        break;
                    case 4:
                        System.out.println("[INFO] Saliendo de la aplicación. ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("[ERROR] Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Por favor, introduzca un número válido.");
            } catch (Exception e) {
                System.out.println("[ERROR] Ocurrió un error inesperado: " + e.getMessage());
            }
        } while (opcion != 4);

        scanner.close();
    }
    
    /**
     * Requisito 1: Dar de alta un nuevo cliente respetando la persistencia previa.
     */
    private static void altaCliente(Scanner scanner) {
        System.out.println("\n--- Alta de Nuevo Cliente ---");
        
        try {
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacío.");

            System.out.print("Año de nacimiento: ");
            int anioNacimiento = Integer.parseInt(scanner.nextLine());
            if (anioNacimiento < 1900 || anioNacimiento > 2026) {
                throw new IllegalArgumentException("Año de nacimiento inválido.");
            }

            System.out.print("Provincia: ");
            String provincia = scanner.nextLine().trim();
            if (provincia.isEmpty()) throw new IllegalArgumentException("La provincia no puede estar vacía.");

            System.out.print("¿Es cliente premium? (true/false): ");
            String premiumInput = scanner.nextLine().trim().toLowerCase();
            if (!premiumInput.equals("true") && !premiumInput.equals("false")) {
                throw new IllegalArgumentException("Debe ingresar obligatoriamente 'true' o 'false'.");
            }
            boolean esPremium = Boolean.parseBoolean(premiumInput);

            System.out.print("Total compras (€): ");
            double totalCompras = Double.parseDouble(scanner.nextLine());
            if (totalCompras < 0) throw new IllegalArgumentException("El total de compras no puede ser negativo.");

            // Guardado directo en el fichero. 
            // El segundo parámetro 'true' activa el modo 'append' (añadir al final)
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME, true))) {
                dos.writeUTF(nombre);
                dos.writeInt(anioNacimiento);
                dos.writeUTF(provincia);
                dos.writeBoolean(esPremium);
                dos.writeDouble(totalCompras);
                System.out.println("[INFO] Cliente guardado correctamente en el fichero.");
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Error de formato numérico: Asegúrese de ingresar valores válidos.");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Validación fallida: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[ERROR] Error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Requisito 2: Mostrar todos los clientes sin usar listas en memoria.
     */
    private static void mostrarClientes() {
        System.out.println("\n--- Listado de Clientes en Fichero ---");
        File file = new File(FILE_NAME);
        
        if (!file.exists() || file.length() == 0) {
            System.out.println("[INFO] El fichero no existe o está vacío. Registre clientes primero.");
            return;
        }

        int contador = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_NAME))) {
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

    /**
     * Requisito 3: Buscar el cliente que más ha comprado directamente sobre el flujo secuencial.
     */
    private static void mostrarMayorComprador() {
        System.out.println("\n--- Cliente con Mayor Volumen de Compras ---");
        File file = new File(FILE_NAME);
        
        if (!file.exists() || file.length() == 0) {
            System.out.println("[INFO] No hay datos disponibles para calcular el mayor comprador.");
            return;
        }

        String maxNombre = "";
        double maxCompras = -1.0; // Inicializador menor al mínimo posible (0.0)

        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_NAME))) {
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
}
