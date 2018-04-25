/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Respuesta;
import clases.Usuario;
import static client.cliente_factura.myClient;
import com.google.gson.Gson;
import java.io.IOException;

/**
 *
 * @author JuanLH
 */
public class cliente_detalle_factura {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static Respuesta buscar_detalle_factura(int id_factura) throws IOException{
         String respuesta = myClient.sentToServerGet(url_base + "buscar_detalle_factura/"+Usuario.token+"/"+id_factura+"");
         Respuesta r = Respuesta.FromJson(respuesta);
         return r;
    }
}
