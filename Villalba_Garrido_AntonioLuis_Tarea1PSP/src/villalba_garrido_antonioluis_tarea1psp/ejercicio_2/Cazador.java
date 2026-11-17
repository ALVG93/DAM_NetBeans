package villalba_garrido_antonioluis_tarea1psp.ejercicio_2;

import java.util.Random;

/**
 * Hilo Productor: Cazador.
 * Dispone de 20 cartuchos de pompas de jabón. Dispara en intervalos aleatorios
 * de 1 a 5 segundos con un 50% de probabilidad de acierto.
 */
public class Cazador extends Thread {

    private static final int TOTAL_CARTUCHOS = 20;
    private final Charca charca;
    private final Random random;

    public Cazador(Charca charca) {
        super("Cazador");
        this.charca = charca;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int cartucho = 1; cartucho <= TOTAL_CARTUCHOS; cartucho++) {
                // Espera aleatoria entre 1 y 5 segundos (1000 a 5000 ms)
                int tiempoEsperaMs = (random.nextInt(5) + 1) * 1000;
                Thread.sleep(tiempoEsperaMs);

                // Probabilidad de acierto del 50%
                if (random.nextBoolean()) {
                    charca.depositarPato(cartucho, TOTAL_CARTUCHOS);
                } else {
                    charca.registrarFallo(cartucho);
                }
            }

            // Mensaje literal indicado en el enunciado al agotar los 20 cartuchos
            System.out.println(Charca.ANSI_CIAN
                    + "Cazador: Se han agotado los cartuchos. Fin de la jornada de tiro."
                    + Charca.ANSI_RESET);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            charca.finalizarCaza();
        }
    }
}