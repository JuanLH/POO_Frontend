/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Cliente;
import clases.Detalle_Pago;
import clases.Respuesta;
import clases.Usuario;
import static client.cliente_cliente.myClient;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author JuanLH
 */
public class cliente_detalle_pago {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static Respuesta insertar_detalle_pago(Detalle_Pago dp) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p1", Integer.toString(dp.getId_factura()));
        frm.param("p2", Float.toString(dp.getMonto()));
        frm.param("p3", Integer.toString(dp.getId_recibo()));
        
        
        String respuesta = myClient.sentToServerPost(url_base + "insertar_detalle_pago", frm);
        System.out.println(respuesta);
        return Respuesta.FromJson(respuesta);
    }
}
