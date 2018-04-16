/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Timestamp;

/**
 *
 * @author JuanLH
 */
public class Entrada_Inventario {
    int id_entrada,id_concepto;
    Timestamp fecha;
    String id_usuario;

    public int getId_entrada() {
        return id_entrada;
    }

    public void setId_entrada(int id_entrda) {
        this.id_entrada = id_entrda;
    }

    public int getId_concepto() {
        return id_concepto;
    }

    public void setId_concepto(int id_concepto) {
        this.id_concepto = id_concepto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}
