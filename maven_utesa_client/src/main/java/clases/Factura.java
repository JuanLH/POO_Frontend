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
public class Factura {
    int id_factura,id_cliente;
    String tipo_factura,id_usuario;
    Timestamp fecha;
    Float monto,balance;

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
    
    public static Factura factura = new Factura();

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTipo_factura() {
        return tipo_factura;
    }

    public void setTipo_factura(String tipo_factura) {
        this.tipo_factura = tipo_factura;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }
    
    
}
