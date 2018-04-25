/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Concepto;
import clases.Producto;
import clases.Respuesta;
import clases.Usuario;
import static client.cliente_producto.myClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.core.Form;

/**
 *
 * @author JuanLH
 */
public class cliente_concepto {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static ArrayList<Concepto> buscar_concepto() throws IOException{
        ArrayList<Concepto> lista=new ArrayList();
        String respuesta = myClient.sentToServerGet(url_base + "buscar_concepto/"+Usuario.token+"");
        System.out.println(respuesta);
        Respuesta response;
        response = json.fromJson(respuesta, Respuesta.class);
        if(response.getId() > 0){
            JsonElement jsonE = new JsonParser().parse(response.getMensaje());
            JsonArray array = jsonE.getAsJsonArray();
            for (JsonElement j : array) {
                Concepto con = new Concepto();
                con = json.fromJson(j, Concepto.class);
                lista.add(con);
            }
            return lista;
        }
        return lista;
    }
    
    public static String insertar_concepto(Concepto c) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p1", c.getDescripcion());
        
        String respuesta = myClient.sentToServerPost(url_base + "insertar_concepto", frm);
        return respuesta;
    }
}
