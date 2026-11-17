package villalba_garrido_antonioluis_tarea1psp.ejercicio_2;

/**
 * Hilo Consumidor: Perro cazador.
 * Compite por recoger los patos abatidos en la charca. Tarda 25 segundos en
 * cada recogida, ladra al estar en su puesto y muestra su estadística al terminar.
 */
public class Perro extends Thread {

    private static final int TIEMPO_RECOGIDA_MS = 25000;
    private final Charca charca;
    private final String nombreSimple;
    private final String colorAnsi;
    private int patosCapturados = 0;

    public Perro(String nombreSimple, String colorAnsi, Charca charca) {
        super("[" + nombreSimple + "]");
        this.nombreSimple = nombreSimple;
        this.colorAnsi = colorAnsi;
        this.charca = charca;
    }

    @Override
    public void run() {
        try {
            // Ladrido inicial al posicionarse al comienzo de la jornada
            System.out.println(colorAnsi + getName() + " Guau!" + Charca.ANSI_RESET);

            while (charca.recogerPato(getName(), colorAnsi)) {
                // Tarda 25 segundos en rastrear, traer el pato y volver a su puesto
                Thread.sleep(TIEMPO_RECOGIDA_MS);

                patosCapturados++;
                System.out.println(colorAnsi + getName() + " Guau!" + Charca.ANSI_RESET);
            }

            // Estadística individual del perro al finalizar su labor
            System.out.println(colorAnsi + "El " + nombreSimple + " terminó. Patos recogidos: "
                    + patosCapturados + Charca.ANSI_RESET);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getPatosCapturados() {
        return patosCapturados;
    }
}