/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clases.Categoria;
import clases.Respuesta;
import clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.core.Form;

/**
 *
 * @author juanbvila
 */
public class cliente_categoria {
    
    static client myClient = new client();
    static final String url_base = "http://localhost:5555/";
    static Gson json = new Gson();
    
    public static ArrayList<Categoria> buscar_categoria() throws IOException{
        ArrayList<Categoria> lista=new ArrayList();
        String respuesta = myClient.sentToServerGet(url_base + "buscar_categoria/"+Usuario.token+"");
        System.out.println(respuesta);
        Respuesta response  = new Respuesta();
        response = json.fromJson(respuesta, Respuesta.class);
        if(response.getId() > 0){
            JsonElement jsonE = new JsonParser().parse(response.getMensaje());
            JsonArray array = jsonE.getAsJsonArray();
            for (JsonElement j : array) {
                Categoria cate = new Categoria();
                cate = json.fromJson(j, Categoria.class);
                lista.add(cate);
            }
            return lista;
        }
        return lista;
    }
    
    public static String insertar_categoria(Categoria c) throws IOException{
        Form frm = new Form();
        frm.param("token", Usuario.token);
        frm.param("p1", c.getDescripcion());
        String respuesta = myClient.sentToServerPost(url_base + "insertar_categoria", frm);
        return respuesta;
    }
    
}
