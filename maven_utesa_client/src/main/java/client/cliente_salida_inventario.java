/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author juanbvila
 */
public class cliente_salida_inventario {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static String insertar_salida_inventario(String js_salida_inv, String js_det_sal) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("js_salida_inv",js_salida_inv);
        frm.param("js_detalle_sal",js_det_sal);
        String respuesta = myClient.sentToServerPost(url_base + "insertar_salida_inventario", frm);
        return respuesta;
    }
    
}
