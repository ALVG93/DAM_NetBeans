package villalba_garrido_antonioluis_tarea1psp.ejercicio_2;

/**
 * Recurso compartido (Monitor) que representa la charca.
 * Controla el límite máximo de 2 patos sin recoger y sincroniza el acceso
 * entre el Cazador y los Perros.
 */
public class Charca {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CIAN = "\u001B[36m";
    public static final String ANSI_MORADO = "\u001B[35m";

    private static final int CAPACIDAD_MAXIMA = 2;
    private int patosPendientes = 0;
    private boolean cazaFinalizada = false;

    /**
     * El cazador deposita un pato abatido en la charca.
     * Si tras depositarlo se alcanza el límite de 2 y aún le quedan cartuchos,
     * espera antes de intentar otro disparo.
     */
    public synchronized void depositarPato(int cartuchosGastados, int totalCartuchos) throws InterruptedException {
        while (patosPendientes >= CAPACIDAD_MAXIMA) {
            wait();
        }

        patosPendientes++;
        System.out.println(ANSI_CIAN + "El cazador ha acertado. (PatosPendientesRecoger: "
                + patosPendientes + " - Cartuchos gastados: " + cartuchosGastados + ")" + ANSI_RESET);

        // Avisamos a los perros de que hay un pato disponible en la charca
        notifyAll();

        // Solo esperamos si hemos llegado a 2 patos Y todavía quedan cartuchos por disparar
        while (patosPendientes >= CAPACIDAD_MAXIMA && cartuchosGastados < totalCartuchos) {
            System.out.println(ANSI_MORADO
                    + "Se ha alcanzado el máximo de patos pendientes de recoger. El cazador espera para probar otro disparo..."
                    + ANSI_RESET);
            wait();
        }
    }

    /**
     * Registra un disparo fallado mostrando el estado actual de la charca.
     */
    public synchronized void registrarFallo(int cartuchosGastados) {
        System.out.println(ANSI_CIAN + "El cazador ha fallado. (PatosPendientesRecoger: "
                + patosPendientes + " - Cartuchos gastados: " + cartuchosGastados + ")" + ANSI_RESET);
    }

    /**
     * Un perro intenta coger un pato de la charca.
     * @return true si logra coger un pato; false si la caza ha terminado y no quedan patos.
     */
    public synchronized boolean recogerPato(String etiquetaPerro, String colorAnsi) throws InterruptedException {
        while (patosPendientes == 0 && !cazaFinalizada) {
            wait();
        }

        if (patosPendientes == 0 && cazaFinalizada) {
            return false;
        }

        patosPendientes--;
        System.out.println(colorAnsi + etiquetaPerro + " Ha cogido un pato." + ANSI_RESET);

        // Notificamos al cazador por si estaba bloqueado esperando hueco
        notifyAll();
        return true;
    }

    /**
     * Marca el fin de los disparos del cazador y despierta a todos los perros
     * que pudieran estar esperando en wait().
     */
    public synchronized void finalizarCaza() {
        cazaFinalizada = true;
        notifyAll();
    }
}