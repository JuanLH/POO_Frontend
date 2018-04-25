/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Recibo;
import clases.Respuesta;
import clases.Usuario;
import static client.cliente_factura.myClient;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author JuanLH
 */
public class cliente_recibo {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static Respuesta buscar_recibos() throws IOException{
         String respuesta = myClient.sentToServerGet(url_base + "buscar_recibos/"+Usuario.token+"");
         Respuesta r = Respuesta.FromJson(respuesta);
         return r;
    }
    
    public static Respuesta buscar_recibos(int id_cliente) throws IOException{
         String respuesta = myClient.sentToServerGet(url_base + "buscar_recibos/"+Usuario.token+"/"+id_cliente+"");
         Respuesta r = Respuesta.FromJson(respuesta);
         return r;
    }
    
    public static Respuesta insertar_recibo(Recibo rec) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p1", rec.getFecha().toString());
        frm.param("p2", Integer.toString(rec.getId_cliente()));
        frm.param("p3", Float.toString(rec.getMonto()));
        frm.param("p4",rec.getConcepto_recibo());
        frm.param("p5", rec.getId_usuario());
        
        String respuesta = myClient.sentToServerPost(url_base + "insertar_recibo", frm);
        System.out.println("Respuesta recibo ->"+respuesta);
        return Respuesta.FromJson(respuesta);
    }
}
