/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import clases.Cliente;
import clases.Factura;
import clases.Respuesta;
import client.cliente_cliente;
import client.cliente_factura;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanLH
 */
public class buscar_factura extends javax.swing.JDialog {

    /**
     * Creates new form buscar_factura
     */
    public buscar_factura() {
        initComponents();
        llenar_tabla();
    }
    
    public buscar_factura(JDialog parent) {
        super(parent,true);
        initComponents();
        llenar_tabla();
    }
    public buscar_factura(JFrame parent) {
        super(parent,true);
        initComponents();
        llenar_tabla();
    }
    
    private void llenar_tabla()
    {
        ArrayList<Factura> lista = new ArrayList();
        Respuesta r = new Respuesta();
            try {
                 r = cliente_factura.buscar_facturas();
                
            } catch (IOException ex) {
                Logger.getLogger(frm_buscar_cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            Gson json = new Gson();
            JsonElement jsonE = new JsonParser().parse(r.getMensaje());
            JsonArray array = jsonE.getAsJsonArray();
            for (JsonElement j : array) {
                Factura fa = new Factura();
                fa = json.fromJson(j, Factura.class);
                lista.add(fa);
            }
            
            DefaultTableModel modelo = new DefaultTableModel();
            String[] col = {"ID","TIPO FACTURA","FECHA","ID_CLIENTE","MONTO","BALANCE","ID_USUARIO"};
            // ciclo for para agregar cada una de las columnas
            for (int i = 0; i < col.length; i++) {
                modelo.addColumn(col[i]);
            }
            
            int k;
            for(Factura f:lista){
                k=0;
                Object[] fila = new Object[7];//El tamaño del vector sera la cantidad de columnas de la tabla
                fila[k++] = (Object)f.getId_factura();
                fila[k++] = (Object)f.getTipo_factura();
                fila[k++] = (Object)f.getFecha();
                fila[k++] = (Object)f.getId_cliente();
                fila[k++] = (Object)f.getMonto();
                fila[k++] = (Object)f.getBalance();
                fila[k++] = (Object)f.getId_usuario();
                
                modelo.addRow(fila);
            }
            tb_factura.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_factura = new javax.swing.JTable();
        btnBuscar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_factura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_facturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_factura);

        btnBuscar1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btnBuscar1.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBuscar1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_facturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_facturaMouseClicked
        // TODO add your handling code here:
        Factura  fac = new Factura();
        fac.setId_factura(Integer.parseInt(tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 0).toString()));
        fac.setTipo_factura(tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 1).toString());
        fac.setFecha(Timestamp.valueOf(tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 2).toString()));
        fac.setId_cliente(Integer.parseInt(tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 3).toString()));
        fac.setMonto((Float)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 4));
        fac.setBalance((Float)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 5));
        fac.setId_usuario((String)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 6));
        Factura.factura = fac;
        new mnt_facturacion(this).setVisible(true);
    }//GEN-LAST:event_tb_facturaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(buscar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscar_factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new buscar_factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_factura;
    // End of variables declaration//GEN-END:variables
}
