package com.mycompany.antonioluis_villalba_ad1_e2.vista;

public class VistaClientes {
    public static void mostrarMensajeInfo(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }

    public static void mostrarError(String error) {
        System.err.println("[ERROR] " + error);
    }

    public static void imprimirResultadoJson(String clientesJson) {
        System.out.println("\n=================================================");
        System.out.println("      RESULTADO DE LA CONVERSIÓN A JSON          ");
        System.out.println("=================================================");
        System.out.println(clientesJson);
        System.out.println("=================================================\n");
    }
}
