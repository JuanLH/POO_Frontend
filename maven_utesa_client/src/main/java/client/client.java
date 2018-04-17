/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class client {

    private javax.ws.rs.client.WebTarget webTarget;
    private javax.ws.rs.client.Client client;

    public client() {
        
    }

    
    public String sentToServerPost(String uri, Form frm) throws IOException {
        
        client = ClientBuilder.newClient();
        webTarget = client.target(uri);
        Response response = null;

        try {
            response = webTarget.request(MediaType.TEXT_PLAIN).post(Entity.form(frm));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Conectando al servidor: \n" + e.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);
        }
        String respuesta = response.readEntity(String.class);
        if (respuesta.equals("")) {
            JOptionPane.showMessageDialog(null, "Error Conectando al servidor \n", "Informacion", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        respuesta = respuesta.replace("\\\"", "\"");

        return respuesta;
    }

    public String sentToServerGet(String uri) throws IOException{
        client = ClientBuilder.newClient();
        webTarget = client.target(uri);
        String respuesta = "";
        try {
            respuesta = webTarget.request(MediaType.TEXT_PLAIN).get(String.class);
            System.err.println("Respusta del server - "+respuesta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "*Error Conectando al servidor: \n" + e.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);
        }

        if (respuesta.equals("")) {
            JOptionPane.showMessageDialog(null, "**Error Conectando al servidor \n", "Informacion", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        return respuesta;
    }
    
}
