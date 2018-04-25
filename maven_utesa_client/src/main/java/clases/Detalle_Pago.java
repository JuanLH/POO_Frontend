/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;



/**
 *
 * @author JuanLH
 */
public class Detalle_Pago {
    int id_detalle_pago,id_factura,id_recibo;
    float monto;

    public int getId_detalle_pago() {
        return id_detalle_pago;
    }

    public void setId_detalle_pago(int id_detalle_pago) {
        this.id_detalle_pago = id_detalle_pago;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(int id_recibo) {
        this.id_recibo = id_recibo;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    
        
    
}
