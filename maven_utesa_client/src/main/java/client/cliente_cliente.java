/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Cliente;
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
public class cliente_cliente {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    
    public static ArrayList<Cliente> buscar_cliente(String nombre) throws IOException{
        ArrayList<Cliente> lista=new ArrayList();
        String respuesta = myClient.sentToServerGet(url_base + "buscar_cliente/"+Usuario.token+"/"+nombre+"");
        System.out.println(respuesta);
        Respuesta response ;
        response = json.fromJson(respuesta, Respuesta.class);
        if(response.getId() > 0){
            JsonElement jsonE = new JsonParser().parse(response.getMensaje());
            JsonArray array = jsonE.getAsJsonArray();
            for (JsonElement j : array) {
                Cliente cli = new Cliente();
                cli = json.fromJson(j, Cliente.class);
                lista.add(cli);
            }
            return lista;
        }
        return lista;
    }
    
    public static Respuesta buscar_cliente(int id) throws IOException{
        Respuesta resp;
        String respuesta = myClient.sentToServerGet(url_base + "buscar_cliente_id/"+Usuario.token+"/"+id+"");
        resp = Respuesta.FromJson(respuesta);
        return resp;
    }
    
    public static String insertar_cliente(Cliente p) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p1", p.getNombre());
        frm.param("p2", p.getApellido());
        frm.param("p3", p.getDireccion());
        frm.param("p4", p.getTelefono());
        frm.param("p5", p.getEmail());
        
        String respuesta = myClient.sentToServerPost(url_base + "insertar_cliente", frm);
        return respuesta;
    }
}
