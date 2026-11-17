package villalba_garrido_antonioluis_tarea1psp.ejercicio_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Proceso 2: Policía.
 * Lee el DNI del conductor por la entrada estándar (pipe), lanza el subproceso
 * BaseDatos con ProcessBuilder pasándole dicho DNI y muestra el resultado.
 */
public class Policia {
    public static void main(String[] args) {
        String dni = leerDniEntradaEstandar();

        if (dni.isEmpty()) {
            System.out.println("Policia: No se ha recibido ningún DNI.");
            return;
        }

        // Mensajes iniciales del control policial
        System.out.println("Policia: Consultando DNI " + dni + " en la Base de Datos...");
        System.out.println("(Conectando con la DGT...)");

        // Lanzamos el proceso BaseDatos y mostramos su respuesta
        consultarBaseDatos(dni);
    }

    /**
     * Lee una línea desde System.in (conectada al proceso Conductor mediante pipe '|').
     */
    private static String leerDniEntradaEstandar() {
        try (Scanner scanner = new Scanner(System.in)) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine().trim();
            }
        }
        
        return "";
    }

    /**
     * Crea y ejecuta el proceso BaseDatos mediante ProcessBuilder pasándole el DNI.
     */
    private static void consultarBaseDatos(String dni) {
        try {
            ProcessBuilder pb = crearProcessBuilder(dni);
            pb.redirectErrorStream(true);

            Process procesoBD = pb.start();

            // Leemos la respuesta que BaseDatos escribe en su salida estándar
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(procesoBD.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            procesoBD.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("Policia: Error al conectar con la Base de Datos -> " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prepara el comando de ejecución tanto si se lanza con 'java Policia.java'
     * dentro de src/paquete como si se ejecuta ya compilado desde Ant/NetBeans.
     */
    private static ProcessBuilder crearProcessBuilder(String dni) {
        String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";

        // Modo fuente directo (recomendado en el enunciado: java Conductor.java | java Policia.java)
        if (new File("BaseDatos.java").exists()) {
            return new ProcessBuilder(javaBin, "BaseDatos.java", dni);
        }

        // Modo compilado (ejecución desde NetBeans o build/classes)
        String classpath = System.getProperty("java.class.path");
        String paquete = Policia.class.getPackage().getName();
        return new ProcessBuilder(javaBin, "-cp", classpath, paquete + ".BaseDatos", dni);
    }
}
