/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Respuesta;
import clases.Usuario;
import static client.cliente_categoria.myClient;
import static client.cliente_entrada_inventario.myClient;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author JuanLH
 */
public class cliente_factura {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static Respuesta buscar_proxima_factura() throws IOException{
         String respuesta = myClient.sentToServerGet(url_base + "buscar_proxima_factura/"+Usuario.token+"");
         Respuesta r = Respuesta.FromJson(respuesta);
         return r;
    }
    
    public static String insertar_factura(String js_factura,String js_detalle_factura) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("js_factura",js_factura);
        frm.param("js_detalle_factura",js_detalle_factura);
        String respuesta = myClient.sentToServerPost(url_base + "insertar_factura", frm);
        return respuesta;
    }
    
    public static Respuesta buscar_facturas() throws IOException{
         String respuesta = myClient.sentToServerGet(url_base + "buscar_factura/"+Usuario.token+"");
         Respuesta r = Respuesta.FromJson(respuesta);
         return r;
    }
    
    
}
