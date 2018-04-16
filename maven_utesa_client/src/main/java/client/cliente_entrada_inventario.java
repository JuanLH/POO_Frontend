/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Producto;
import clases.Usuario;
import static client.cliente_producto.myClient;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author JuanLH
 */
public class cliente_entrada_inventario {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static String insertar_entrada_inv(String js_ent_inv,String js_det_ent) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("js_entrada_inv",js_ent_inv);
        frm.param("js_detalle_ent",js_det_ent);
        String respuesta = myClient.sentToServerPost(url_base + "insertar_entrada_inventario", frm);
        return respuesta;
    }
}
