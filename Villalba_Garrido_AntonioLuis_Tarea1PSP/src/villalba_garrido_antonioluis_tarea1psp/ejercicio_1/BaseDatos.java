package villalba_garrido_antonioluis_tarea1psp.ejercicio_1;

import java.util.Random;

/**
 * Proceso 3: BaseDatos.
 * Recibe un DNI por argumentos, simula una consulta de 2 segundos a la DGT
 * y devuelve por la salida estándar si tiene multas pendientes.
 */
public class BaseDatos {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.out.println("BaseDatos: Error, no se recibió ningún DNI.");
            System.exit(1);
        }

        String dni = args[0].trim().toUpperCase();

        try {
            // Simulamos el tiempo de consulta de 2 segundos
            Thread.sleep(2000);

            // Determinamos de forma aleatoria si tiene multas pendientes
            boolean tieneMultas = new Random().nextBoolean();

            if (tieneMultas) {
                System.out.println("BaseDatos: DNI " + dni + " con multas pendientes. Debe apartarse para seguir hablando.");
            } else {
                System.out.println("BaseDatos: DNI " + dni + " sin multas pendientes. Puede continuar su marcha.");
            }

        } catch (InterruptedException e) {
            System.out.println("BaseDatos: Consulta interrumpida.");
            Thread.currentThread().interrupt();
            System.exit(1);
        }
    }
}
