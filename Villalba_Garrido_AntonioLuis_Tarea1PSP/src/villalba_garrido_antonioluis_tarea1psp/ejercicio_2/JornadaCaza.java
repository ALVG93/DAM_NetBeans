package villalba_garrido_antonioluis_tarea1psp.ejercicio_2;

import java.util.ArrayList;

/**
 * Clase principal que orquesta la simulación concurrente de la jornada de caza.
 */
public class JornadaCaza {
    private static final String ANSI_ROJO = "\u001B[31m";
    private static final String ANSI_VERDE = "\u001B[32m";
    private static final String ANSI_AMARILLO = "\u001B[33m";

    public static void main(String[] args) {
        Charca charca = new Charca();
        Cazador cazador = new Cazador(charca);

        ArrayList<Perro> perros = new ArrayList<>();
        perros.add(new Perro("PERRO1", ANSI_ROJO, charca));
        perros.add(new Perro("PERRO2", ANSI_VERDE, charca));
        perros.add(new Perro("PERRO3", ANSI_AMARILLO, charca));

        // Arrancamos los hilos de los perros y del cazador
        for (Perro perro : perros) {
            perro.start();
        }
        
        cazador.start();

        // Esperamos a que tanto el cazador como los 3 perros finalicen completamente
        try {
            cazador.join();
            
            for (Perro perro : perros) {
                perro.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Mensaje final cuando la charca está vacía y todos los perros han regresado
        System.out.println("¡Otro día de caza finalizado!");
    }
}