/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import clases.Cliente;
import clases.Detalle_Pago;
import clases.Factura;
import clases.Recibo;
import clases.Respuesta;
import clases.Usuario;
import client.cliente_detalle_pago;
import client.cliente_factura;
import client.cliente_recibo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanLH
 */
public class mnt_recibo extends javax.swing.JDialog {

    /**
     * Creates new form mnt_recibo
     */
    DefaultTableModel modelo;
    Factura factura = new Factura();
    
    public mnt_recibo() {
        initComponents();
        modelo = new DefaultTableModel();
        llenar_columnas();
    }
    
    public mnt_recibo(JDialog parent) {
        super(parent,true);
        initComponents();
        modelo = new DefaultTableModel();
        llenar_columnas();
    }
    
    public mnt_recibo(JFrame parent) {
        super(parent,true);
        initComponents();
        modelo = new DefaultTableModel();
        llenar_columnas();
    }
    
    private void limpiar_form(){
        modelo = new DefaultTableModel();
        tb_factura.setModel(modelo);
        llenar_columnas();
        txtCliente.setText("");
        txtTotal.setText("");
        txt_pendiente.setText("");
        txtMonto.setText("");
        btn_pagar.setEnabled(false);
        
    }
    
    private void llenar_columnas(){
          modelo = new DefaultTableModel();
            String[] col = {"ID_FACTURA","FECHA","MONTO","BALANCE","PAGO"};
            // ciclo for para agregar cada una de las columnas
            for (int i = 0; i < col.length; i++) {
                modelo.addColumn(col[i]);
            }
            
      }
    
    private void llenar_tabla(){
        try {
            Respuesta resp = cliente_factura.buscar_facturas_forRecibo(Cliente.cliente.getId());
            if(resp.getId()>0){
                ArrayList<Factura>lista=new ArrayList();
                Gson json = new Gson();
                JsonElement jsonE = new JsonParser().parse(resp.getMensaje());
                JsonArray array = jsonE.getAsJsonArray();
                for (JsonElement j : array) {
                    Factura fac;
                    fac = json.fromJson(j, Factura.class);
                    lista.add(fac);
                }
                
                for(Factura fac:lista){
                    int k=0;
                    Object[] fila = new Object[5];//El tamaño del vector sera la cantidad de columnas de la tabla
                    fila[k++] = (Object)fac.getId_factura();
                    fila[k++] = (Object)fac.getFecha();
                    fila[k++] = (Object)fac.getMonto();
                    fila[k++] = (Object)fac.getBalance();
                    fila[k++] = (Object)0;

                    modelo.addRow(fila);
                }
                tb_factura.setModel(modelo);
            }
        } catch (IOException ex) {
            Logger.getLogger(mnt_recibo.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConcepto = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_factura = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_pendiente = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        btn_pagar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_pagar1 = new javax.swing.JButton();
        btn_pagar2 = new javax.swing.JButton();
        btn_pagar3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CREAR RECIBOS");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("Cliente:");

        txtCliente.setEnabled(false);

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtConcepto.setColumns(20);
        txtConcepto.setRows(5);
        jScrollPane1.setViewportView(txtConcepto);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Elegir factura a pagar ");

        tb_factura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tb_facturaMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_factura);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel3.setText("Balance Pendiente:");

        txt_pendiente.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel4.setText("Monto:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Abonar");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Saldar");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });

        btn_pagar.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btn_pagar.setText("Abonar");
        btn_pagar.setEnabled(false);
        btn_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_pendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Concepto:");

        txtTotal.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel6.setText("Total de Recibo:");

        btn_pagar1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btn_pagar1.setText("Salvar");
        btn_pagar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagar1ActionPerformed(evt);
            }
        });

        btn_pagar2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btn_pagar2.setText("Nuevo");
        btn_pagar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagar2ActionPerformed(evt);
            }
        });

        btn_pagar3.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btn_pagar3.setText("Salir");
        btn_pagar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2)))
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btn_pagar1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_pagar2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_pagar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_pagar1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pagar2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_pagar3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new frm_buscar_cliente(this).setVisible(true);
        txtCliente.setText(Cliente.cliente.getNombre());
        llenar_tabla();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagarActionPerformed
        // TODO add your handling code here:
        if(!txtMonto.getText().isEmpty()){
            tb_factura.setValueAt(txtMonto.getText(),tb_factura.getSelectedRow(), 4);
            float acum = 0;
            for(int x=0;x<tb_factura.getRowCount();x++){
                float pago = Float.parseFloat(tb_factura.getValueAt(x, 4).toString());
                acum = acum + pago;
                
            }
            txtTotal.setText(acum+"");
        }
        btn_pagar.setEnabled(false);
        txtMonto.setText("");
    }//GEN-LAST:event_btn_pagarActionPerformed

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        // TODO add your handling code here:
        btn_pagar.setText("Saldar");
        txtMonto.setText(Float.toString(factura.getBalance()));
    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        // TODO add your handling code here:
        btn_pagar.setText("Abonar");
        txtMonto.setText("0");
    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void tb_facturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_facturaMousePressed
        // TODO add your handling code here:
        factura.setId_factura((int)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 0));
        factura.setFecha((Timestamp)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 1));
        factura.setMonto((Float)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 2));
        factura.setBalance((Float)tb_factura.getModel().getValueAt(tb_factura.getSelectedRow(), 3));
        txt_pendiente.setText(Float.toString(factura.getBalance()));
        btn_pagar.setEnabled(true);
        
    }//GEN-LAST:event_tb_facturaMousePressed

    private void btn_pagar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagar1ActionPerformed
        if(!txtTotal.getText().isEmpty()){
            Respuesta resp = new Respuesta();
            Recibo recibo = new Recibo();
            recibo.setFecha(new Timestamp(new Date().getTime()));
            recibo.setId_cliente(Cliente.cliente.getId());
            recibo.setMonto(Float.parseFloat(txtTotal.getText()));
            recibo.setConcepto_recibo(txtConcepto.getText());
            recibo.setId_usuario(Usuario.user.getId_usuario());
            try {
               resp = cliente_recibo.insertar_recibo(recibo);
            } catch (IOException ex) {
                Logger.getLogger(mnt_recibo.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            
            int id_recibo = Integer.parseInt(resp.getMensaje());
            System.out.println("Recibo id ->"+id_recibo);
            
            for(int x=0;x<tb_factura.getRowCount();x++){
                Detalle_Pago dp = new Detalle_Pago();
                dp.setId_recibo(id_recibo);
                dp.setId_factura(Integer.parseInt(tb_factura.getValueAt(x, 0).toString()));
                dp.setMonto(Float.parseFloat(tb_factura.getValueAt(x, 4).toString()));
                try {
                    System.out.println("Chequeando ----");
                    System.out.println(cliente_detalle_pago.insertar_detalle_pago(dp));
                    System.out.println("Chequeando ----");
                } catch (IOException ex) {
                    System.out.println("Chequeando ----");
                    Logger.getLogger(mnt_recibo.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            limpiar_form();
        }
        
        /*if(tb_factura.getRowCount()>0){
            
        }*/
    }//GEN-LAST:event_btn_pagar1ActionPerformed

    private void btn_pagar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pagar2ActionPerformed

    private void btn_pagar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pagar3ActionPerformed

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
            java.util.logging.Logger.getLogger(mnt_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mnt_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mnt_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mnt_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mnt_recibo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pagar;
    private javax.swing.JButton btn_pagar1;
    private javax.swing.JButton btn_pagar2;
    private javax.swing.JButton btn_pagar3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb_factura;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextArea txtConcepto;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txt_pendiente;
    // End of variables declaration//GEN-END:variables
}