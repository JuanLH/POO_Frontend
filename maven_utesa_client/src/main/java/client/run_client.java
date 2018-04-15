/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author Michael Jared Diaz
 */
public class run_client {

    public static void main(String a[]) throws IOException {

        client myClient = new client();
        String respuesta, url_base = "http://localhost:5555/";

        //GET 
        respuesta = myClient.sentToServerGet(url_base + "hello");
        System.err.println("\n\n\nGET -> \n" + respuesta);

        respuesta = myClient.sentToServerGet(url_base + "sumar_cantidades/44/20");
        System.err.println("\n\n\nGET Enviando Parametros-> \n" + respuesta);

        //--------------------------------------
        //POST 
        respuesta = myClient.sentToServerPost(url_base + "hello", null);
        System.err.println("\n\n\nPOST -> \n" + respuesta);

        //POST ENVIADO PARAMETROS POR FORM
        Form frm = new Form();
        frm.param("n1", "50");
        frm.param("n2", "30");
        respuesta = myClient.sentToServerPost(url_base + "sumar_cantidades", frm);
        System.err.println("\n\n\nPOST Enviando Parametros Form-> \n" + respuesta);

        //POST ENVIADO PARAMETROS POR URL
        respuesta = myClient.sentToServerPost(url_base + "sumar_cantidades/10/20", null);
        System.err.println("\n\n\nPOST Enviando Parametros URL-> \n" + respuesta);

    }

}
