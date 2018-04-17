/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import clases.Cliente;
import clases.Producto;
import client.cliente_cliente;
import client.cliente_producto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JuanLH
 */
public class frm_buscar_cliente extends javax.swing.JDialog {

    /**
     * Creates new form frm_buscar_cliente
     */
    public frm_buscar_cliente() {
        initComponents();
    }
    
    public frm_buscar_cliente(JDialog parent){
        super(parent,true);
        initComponents();
    }
    
    public frm_buscar_cliente(JFrame parent){
        super(parent,true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_cliente = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre de Cliente:");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tb_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_clienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_cliente);

        jButton2.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!txtNombreCliente.getText().isEmpty()){
            ArrayList<Cliente> lista = new ArrayList();
            try {
                lista = cliente_cliente.buscar_cliente(txtNombreCliente.getText());
            } catch (IOException ex) {
                Logger.getLogger(frm_buscar_cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            DefaultTableModel modelo = new DefaultTableModel();
            String[] col = {"ID","NOMBRE","APELLIDO","DIRECCION","TELEFONO","EMAIL"};
            // ciclo for para agregar cada una de las columnas
            for (int i = 0; i < col.length; i++) {
                modelo.addColumn(col[i]);
            }
            
            int k;
            for(Cliente p:lista){
                k=0;
                Object[] fila = new Object[6];//El tamaño del vector sera la cantidad de columnas de la tabla
                fila[k++] = (Object)p.getId();
                fila[k++] = (Object)p.getNombre();
                fila[k++] = (Object)p.getApellido();
                fila[k++] = (Object)p.getDireccion();
                fila[k++] = (Object)p.getTelefono();
                fila[k++] = (Object)p.getEmail();
                
                modelo.addRow(fila);
            }
            tb_cliente.setModel(modelo);
            
            
        }
        else{
            JOptionPane.showMessageDialog(null, "debe llenar el campo ", "Informacion", JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_clienteMouseClicked
        // TODO add your handling code here:
        Cliente p = new Cliente();
        p.setId((int)tb_cliente.getModel().getValueAt(tb_cliente.getSelectedRow(), 0));
        p.setNombre((String)tb_cliente.getModel().getValueAt(tb_cliente.getSelectedRow(), 1));
        p.setApellido((String)tb_cliente.getModel().getValueAt(tb_cliente.getSelectedRow(), 2));
        p.setDireccion((String)tb_cliente.getModel().getValueAt(tb_cliente.getSelectedRow(), 3));
        p.setTelefono((String)tb_cliente.getModel().getValueAt(tb_cliente.getSelectedRow(), 4));
        p.setEmail((String)tb_cliente.getModel().getValueAt(tb_cliente.getSelectedRow(), 5));
        
        
        Cliente.cliente = p;//Guardando en la variable static para poder buscarla desde el otro form
        this.dispose();
    }//GEN-LAST:event_tb_clienteMouseClicked

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
            java.util.logging.Logger.getLogger(frm_buscar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_buscar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_buscar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_buscar_cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_buscar_cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_cliente;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}