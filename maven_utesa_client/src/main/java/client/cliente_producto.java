/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Producto;
import clases.Respuesta;
import clases.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.core.Form;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 *
 * @author JuanLH
 */
public class cliente_producto {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static ArrayList<Producto> buscar_producto(String nombre) throws IOException{
        ArrayList<Producto> lista=new ArrayList();
        String respuesta = myClient.sentToServerGet(url_base + "buscar_producto/"+Usuario.token+"/"+nombre+"");
        System.out.println(respuesta);
        Respuesta response  = new Respuesta();
        response = json.fromJson(respuesta, Respuesta.class);
        if(response.getId() > 0){
            JsonElement jsonE = new JsonParser().parse(response.getMensaje());
            JsonArray array = jsonE.getAsJsonArray();
            for (JsonElement j : array) {
                Producto prod = new Producto();
                prod = json.fromJson(j, Producto.class);
                lista.add(prod);
            }
            return lista;
        }
        return lista;
    }
    
    public static String insertar_producto(Producto p) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p2", p.getDescripcion());
        frm.param("p3", Integer.toString(p.getId_categoria()));
        frm.param("p4", Float.toString(p.getPrecio()));
        frm.param("p5", Float.toString(p.getCosto()));
        frm.param("p6", Float.toString(p.getExistencia()));
        frm.param("p7", Float.toString(p.getTax()));
        String respuesta = myClient.sentToServerPost(url_base + "insertar_producto", frm);
        return respuesta;
    }
    
    
}
