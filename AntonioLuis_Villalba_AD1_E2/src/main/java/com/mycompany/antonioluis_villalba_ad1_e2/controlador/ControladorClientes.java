package com.mycompany.antonioluis_villalba_ad1_e2.controlador;

import com.mycompany.antonioluis_villalba_ad1_e2.modelo.Cliente;
import com.mycompany.antonioluis_villalba_ad1_e2.vista.VistaClientes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ControladorClientes {
    public void ejecutarConversor() {
        String rutaFicheroXml = "clientes.xml";
        VistaClientes.mostrarMensajeInfo("Iniciando el proceso de conversión de archivos...");
        VistaClientes.mostrarMensajeInfo("Leyendo el origen de datos: " + rutaFicheroXml);

        try {
            // 1. Leer los datos desde el fichero XML
            ArrayList<Cliente> clientes = leerClientesDesdeXML(rutaFicheroXml);
            VistaClientes.mostrarMensajeInfo("Fichero XML leído con éxito. Nodos detectados: " + clientes.size());

            // 2. Procesar la conversión a formato JSON
            VistaClientes.mostrarMensajeInfo("Procesando la estructura a formato JSON...");
            String jsonResultado = convertirListaAJson(clientes);

            // 3. Mostrar el resultado por la consola de NetBeans
            VistaClientes.imprimirResultadoJson(jsonResultado);
            VistaClientes.mostrarMensajeInfo("Proceso finalizado correctamente.");

        } catch (Exception ex) {
            VistaClientes.mostrarError("Se ha producido un error durante el procesamiento de los ficheros.");
            VistaClientes.mostrarError("Detalle técnico: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private ArrayList<Cliente> leerClientesDesdeXML(String rutaXML) throws Exception {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        File archivo = new File(rutaXML);

        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo " + rutaXML + " no existe.");
        }

        // Configuración del Parser DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(archivo);
        doc.getDocumentElement().normalize();

        // Obtener todos los nodos <cliente>
        NodeList nList = doc.getElementsByTagName("cliente");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;

                String nombre = elemento.getElementsByTagName("nombreCompleto").item(0).getTextContent();
                int anio = Integer.parseInt(elemento.getElementsByTagName("anioNacimiento").item(0).getTextContent());
                String provincia = elemento.getElementsByTagName("provincia").item(0).getTextContent();
                boolean premium = Boolean.parseBoolean(elemento.getElementsByTagName("premium").item(0).getTextContent());
                double compras = Double.parseDouble(elemento.getElementsByTagName("totalCompras").item(0).getTextContent());

                listaClientes.add(new Cliente(nombre, anio, provincia, premium, compras));
            }
        }
        return listaClientes;
    }

    /**
     * Toma la lista de objetos cliente y la transforma en una cadena JSON formateada
     */
    private String convertirListaAJson(ArrayList<Cliente> clientes) {
        JSONArray jsonArray = new JSONArray();

        for (Cliente c : clientes) {
            JSONObject jsonCliente = new JSONObject();
            jsonCliente.put("nombreCompleto", c.getNombreCompleto());
            jsonCliente.put("anioNacimiento", c.getAnioNacimiento());
            jsonCliente.put("provincia", c.getProvincia());
            jsonCliente.put("premium", c.isPremium());
            jsonCliente.put("totalCompras", c.getTotalCompras());

            jsonArray.put(jsonCliente);
        }

        // El parámetro indentFactor (4) hace un "pretty print" para que el JSON se vea ordenado en la terminal
        return jsonArray.toString(4);
    }
}
