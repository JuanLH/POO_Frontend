/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import clases.Cliente;
import clases.Concepto;
import clases.Detalle_Factura;
import clases.Factura;
import clases.Producto;
import clases.Respuesta;
import clases.Usuario;
import client.cliente_cliente;
import client.cliente_detalle_factura;
import client.cliente_factura;
import client.cliente_producto;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanbvila
 */
public class mnt_facturacion extends javax.swing.JDialog {

    /**
     * Creates new form Facturacion
     */
    DefaultTableModel modelo ;
    
    public mnt_facturacion() {
        initComponents();
        llenar_columnas();
    }
    
    public mnt_facturacion(JFrame parent) {
        super(parent,true);
        initComponents();
        
        llenar_num_factura();
        
        btnResta.setEnabled(false);
        modelo = new DefaultTableModel();
        llenar_columnas();
        
    }
    

    public mnt_facturacion(JDialog parent) {
        super(parent,true);
        initComponents();
        
        llenar_columnas();
        Respuesta resp = new  Respuesta();
        try {
            resp = cliente_cliente.buscar_cliente(Factura.factura.getId_cliente());
        } catch (IOException ex) {
            Logger.getLogger(mnt_facturacion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        Gson json = new Gson();
        Cliente cliente = json.fromJson(resp.getMensaje(), Cliente.class);
        txtCliente.setText(cliente.getNombre());
        txtTelefono.setText(cliente.getTelefono());
        txtNumFac.setText(""+Factura.factura.getId_factura());
        lblfecha.setText(Factura.factura.getFecha().toString());
        if(Factura.factura.getTipo_factura().equals("CREDITO"))
            cmbTipo.setSelectedIndex(0);
        else
            cmbTipo.setSelectedIndex(1);
        try {
        resp = cliente_detalle_factura.buscar_detalle_factura(Factura.factura.getId_factura());
        } catch (IOException ex) {
        Logger.getLogger(mnt_facturacion.class.getName())
                .log(Level.SEVERE, null, ex);
        }
        ArrayList<Detalle_Factura> lista = new ArrayList();
        if(resp.getId() > 0){
            JsonElement jsonE = new JsonParser().parse(resp.getMensaje());
            JsonArray array = jsonE.getAsJsonArray();
            for (JsonElement j : array) {
                Detalle_Factura fac = new Detalle_Factura();
                fac = json.fromJson(j, Detalle_Factura.class);
                lista.add(fac);
            }
            
        }
        
        llenar_columnas();
        int k;
            for(Detalle_Factura p:lista){
                try {
                    resp = cliente_producto.buscar_producto(p.getReferencia());
                } catch (IOException ex) {
                    Logger.getLogger(mnt_facturacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                Producto pro = json.fromJson(resp.getMensaje(), Producto.class);
                k=0;
                Object[] fila = new Object[7];//El tamaño del vector sera la cantidad de columnas de la tabla
                fila[k++] = (Object)p.getReferencia();
                fila[k++] = (Object)pro.getDescripcion();
                fila[k++] = (Object)p.getPrecio();
                fila[k++] = (Object)p.getCantidad();
                fila[k++] = (Object)p.getTax();
                fila[k++] = (Object)p.getCosto();
                fila[k++] = (Object)(p.getPrecio()*p.getCantidad());
                
                modelo.addRow(fila);
            }
            tb_detallef.setModel(modelo);
            sumar_total();
    }
    
    private void llenar_num_factura(){
        try {
            Respuesta f = cliente_factura.buscar_proxima_factura();
            int num = Integer.parseInt(f.getMensaje()) + 1;
            txtNumFac.setText(""+num);
        } catch (IOException ex) {
            Logger.getLogger(mnt_facturacion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    private void llenar_columnas(){
          modelo = new DefaultTableModel();
            String[] col = {"REFERENCIA","DESCRIPCION","PRECIO","QTY","ITBIS","COSTO","TOTAL"};
            // ciclo for para agregar cada una de las columnas
            for (int i = 0; i < col.length; i++) {
                modelo.addColumn(col[i]);
            }
            
      }
    
    private void limpiar_form(){
          txtTelefono.setText("");
          txtCliente.setText("");
          txtNumFac.setText("");
          txtReferencia.setText("");
          txtNomPro.setText("");
          txtCantidad.setText("");
          
          txtSubTotal.setText("");
          txtTax.setText("");
          txtTotal.setText("");
          
          modelo =new DefaultTableModel(); 
          tb_detallef.setModel(modelo);
          llenar_columnas();
      }

    private void sumar_total(){
        int cant_filas = tb_detallef.getModel().getRowCount();
            if( cant_filas > 0){
                Float acum_total = 0.0f;
                for(int x=0;x<cant_filas;x++){
                    Float total_prod =(Float)tb_detallef.getModel().getValueAt(x,6);
                    acum_total = acum_total + total_prod;  
                }
                
                Float acum_itbis = 0.0f;
                for(int x=0;x<cant_filas;x++){
                    Float itbis =(Float)tb_detallef.getModel().getValueAt(x,4);
                    acum_itbis = acum_itbis + itbis;  
                }
                txtSubTotal.setText(Float.toString(acum_total));
                txtTax.setText(Float.toString(acum_itbis));
                float total = acum_itbis + acum_total;
                txtTotal.setText(Float.toString(total));
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

        jLabel1 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNumFac = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtReferencia = new javax.swing.JTextField();
        txtNomPro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_detallef = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnSuma = new javax.swing.JButton();
        btnResta = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        txtTax = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURACION");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("Cliente:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CREDITO", "DEBITO" }));
        cmbTipo.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Numero de Factura");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel3.setText("Fecha");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel4.setText("Telefono");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel6.setText("Cantidad");

        tb_detallef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_detallefMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_detallef);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel7.setText("Referencia");

        btnSuma.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnSuma.setText("+");
        btnSuma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumaActionPerformed(evt);
            }
        });

        btnResta.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnResta.setText("-");
        btnResta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btnBuscar.setText("Buscar");

        btnNuevo.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel8.setText("Tax");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("SubTotal");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel9.setText("Total");

        btnSalir.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel10.setText("Tipo de Factura");

        lblfecha.setText("17/04/2018");

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnSalvar)
                                            .addComponent(btnBuscar))
                                        .addGap(373, 373, 373)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnNuevo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSalir)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSuma, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnResta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblfecha))
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNumFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblfecha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnSuma, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumaActionPerformed
        // TODO add your handling code here:
        int indicador = 0;
            int cant_filas = tb_detallef.getModel().getRowCount();
            if( cant_filas > 0){
                for(int x=0;x<cant_filas;x++){
                    String ref =(String)tb_detallef.getModel().getValueAt(x,0);
                    if(ref.equals(txtReferencia.getText())){
                        int cant = Integer.parseInt(tb_detallef.getModel().getValueAt(x,3).toString());
                        int cant2 = Integer.parseInt(txtCantidad.getText());
                        float precio = Producto.producto.getPrecio();
                        float total = (cant+cant2)*precio;
                        tb_detallef.getModel().setValueAt((cant+cant2), x, 3);
                        tb_detallef.getModel().setValueAt(total, x, 6);
                        indicador = 1;
                        break;
                    }
                }
            }
        if(indicador == 0){
                Object[] fila = new Object[7];//El tamaño del vector sera la cantidad de columnas de la tabla
                int k=0;
                fila[k++] = (Object)txtReferencia.getText();
                fila[k++] = (Object)txtNomPro.getText();
                fila[k++] = (Object)Producto.producto.getPrecio();
                fila[k++] = (Object)txtCantidad.getText();
                fila[k++] = (Object)Producto.producto.getTax();
                fila[k++] = (Object)Producto.producto.getCosto();
                fila[k++] = (Object)((Producto.producto.getPrecio())*(Float.parseFloat(txtCantidad.getText()))); 
                modelo.addRow(fila);
                tb_detallef.setModel(modelo);
            }
            sumar_total();
            //limpiar_form();
            
    }//GEN-LAST:event_btnSumaActionPerformed

    private void btnRestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaActionPerformed
        // TODO add your handling code here:
        modelo.removeRow(tb_detallef.getSelectedRow());
        btnResta.setEnabled(false);
    }//GEN-LAST:event_btnRestaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new frm_buscar_cliente(this).setVisible(true);
        txtCliente.setText(Cliente.cliente.getNombre());
        txtTelefono.setText(Cliente.cliente.getTelefono());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new frm_buscar_producto(this).setVisible(true);
        txtReferencia.setText(Producto.producto.getReferencia());
        txtNomPro.setText(Producto.producto.getDescripcion());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(!txtCliente.getText().isEmpty() || tb_detallef.getModel().getRowCount()>0){
            Factura fac = new Factura();
            fac.setTipo_factura(cmbTipo.getSelectedItem().toString());
            fac.setFecha( new Timestamp(new Date().getTime()));
            fac.setId_cliente(Cliente.cliente.getId());
            fac.setMonto(Float.parseFloat(txtTotal.getText()));
            fac.setId_usuario(Usuario.user.getId_usuario());
            
            ArrayList<Detalle_Factura> lista = new ArrayList();
            int cant_filas = tb_detallef.getModel().getRowCount();
            for(int x=0;x<cant_filas;x++){
                Detalle_Factura dt = new Detalle_Factura();
                dt.setReferencia(Integer.parseInt(tb_detallef.getModel().getValueAt(x,0).toString()));
                dt.setCantidad(Float.parseFloat(tb_detallef.getModel().getValueAt(x, 3).toString()));
                dt.setPrecio(Float.parseFloat(tb_detallef.getModel().getValueAt(x,2).toString()));
                dt.setTax(Float.parseFloat(tb_detallef.getModel().getValueAt(x, 4).toString()));
                dt.setCosto(Float.parseFloat(tb_detallef.getModel().getValueAt(x, 5).toString()));
                lista.add(dt);
            }
            
            if(!lista.isEmpty()){
                Gson json = new Gson();
                String json_factura = json.toJson(fac);
                String  json_det_fac = json.toJson(lista);
                
                try {
                    cliente_factura.insertar_factura(json_factura, json_det_fac);
                    limpiar_form();
                } catch (IOException ex) {
                    Logger.getLogger(mnt_facturacion.class.getName())
                        .log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "No hay items en la factura");
            }
            
             
        }
        else{
            JOptionPane.showMessageDialog(this, "No hay datos en la factura");
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void tb_detallefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_detallefMouseClicked
        // TODO add your handling code here:+
        btnResta.setEnabled(true);
    }//GEN-LAST:event_tb_detallefMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(mnt_facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mnt_facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mnt_facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mnt_facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mnt_facturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnResta;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSuma;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JTable tb_detallef;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtNomPro;
    private javax.swing.JTextField txtNumFac;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
