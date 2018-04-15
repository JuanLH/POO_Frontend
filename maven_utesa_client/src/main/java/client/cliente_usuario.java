/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Respuesta;
import clases.Usuario;
import static client.cliente_producto.myClient;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.core.Form;

/**
 *
 * @author JuanLH
 */
public class cliente_usuario {
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static String insertar_usuario(Usuario u) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p1", u.getId_usuario());
        frm.param("p2", u.getNombre());
        frm.param("p3", u.getPassword());
        
        String respuesta = myClient.sentToServerPost(url_base + "insertar_usuario", frm);
        return respuesta;
    }
    
    public static Respuesta login(String id_usuario,String pass) throws IOException{
        String respuesta = myClient.sentToServerGet(url_base + "login/"+id_usuario+"/"+pass+"");
        Respuesta response;
        response = json.fromJson(respuesta, Respuesta.class);
        if(response.getId()>0){
            Usuario.token = response.getMensaje();
            return response;
        }
        else{
            return response;
        }
        
    }
    public static void logout(String id_usuario) throws IOException{
        String respuesta = myClient.sentToServerGet(url_base + "logout/"+Usuario.token+"/"+id_usuario+"");
        System.out.println(respuesta);
    }
}
