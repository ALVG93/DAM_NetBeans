package villalba_garrido_antonioluis_tarea1psp.ejercicio_1;

/**
 * Proceso 1: Conductor.
 * Envía su DNI por la salida estándar para que sea recogido por el proceso Policía
 * a través de una tubería (pipe).
 */
public class Conductor {
    public static void main(String[] args) {
        // Si se pasa un DNI como argumento se utiliza; si no, se usa uno por defecto
        String dni = (args.length > 0) ? args[0] : "12345678X";

        // Se imprime únicamente el DNI limpio en la salida estándar
        System.out.println(dni);
    }
}
