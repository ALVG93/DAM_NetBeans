package com.mycompany.antonioluis_villalba_ad1_e2.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private static final String FICHERO_XML = "clientes.xml";
    
    public void ejecutarConversor() {
        VistaClientes.mostrarMensajeInfo("Iniciando el proceso de conversión de XML a JSON desde un archivo...");
        VistaClientes.mostrarMensajeInfo("Leyendo el archivo: " + FICHERO_XML);

        try {
            ArrayList<Cliente> clientes = leerClientesDesdeXML(FICHERO_XML);
            VistaClientes.mostrarMensajeInfo("Fichero XML leído correctamente. Clientes leidos: " + clientes.size());

            VistaClientes.mostrarMensajeInfo("Convirtiendo a formato JSON...");
            String jsonResultado = convertirListaAJson(clientes);

            VistaClientes.imprimirResultadoJson(jsonResultado);
            VistaClientes.mostrarMensajeInfo("Conversión realizada correctamente.");

        } catch (Exception ex) {
            VistaClientes.mostrarError("Se ha producido un error durante el procesamiento de conversión.");
            VistaClientes.mostrarError("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private ArrayList<Cliente> leerClientesDesdeXML(String rutaXml) throws Exception {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        File archivoXml = new File(rutaXml);

        if (!archivoXml.exists()) {
            throw new FileNotFoundException("El archivo '" + rutaXml + "' no se encuentra en la raíz del proyecto.");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(archivoXml);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("cliente");
        
        Cliente clienteLeido;

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                clienteLeido = new Cliente();
                
                leerDatosCliente((Element) node, clienteLeido);
                
                listaClientes.add(clienteLeido);
            }
        }
        
        return listaClientes;
    }
    
    private void leerDatosCliente(Element elemento, Cliente clienteLeido) {
        clienteLeido.setNombreCompleto(
                elemento.getElementsByTagName("nombreCompleto").item(0).getTextContent());
        
        clienteLeido.setAnioNacimiento(
                Integer.parseInt(elemento.getElementsByTagName("anioNacimiento").item(0).getTextContent()));
        
        clienteLeido.setProvincia(
                elemento.getElementsByTagName("provincia").item(0).getTextContent());
        
        clienteLeido.setPremium(
                Boolean.parseBoolean(elemento.getElementsByTagName("premium").item(0).getTextContent()));
        
        clienteLeido.setTotalCompras(
                Double.parseDouble(elemento.getElementsByTagName("totalCompras").item(0).getTextContent()));
    }

    private String convertirListaAJson(ArrayList<Cliente> listaClientes) {
        // Usamos setPrettyPrinting para que el JSON se genere estructurado y sangrado en consola
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Esta manera se emplearía si queremos que el JSON quede en una linea
        // Gson gson = new Gson();
        
        // Gson convierte de forma directa la colección de objetos a estructura JSON []
        return gson.toJson(listaClientes);
    }
}