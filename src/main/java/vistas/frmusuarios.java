/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.UsuarioJpaController;
import controladores.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;

/**
 *
 * @author Justino
 */
public class frmusuarios extends javax.swing.JInternalFrame {
private DefaultComboBoxModel comboModelo;
       UsuarioJpaController controller;
    /**
     * Creates new form frmusuarios
     */
    public frmusuarios() {
        initComponents();
//        llenacombo();
        inhabilitar();
        mostrar(txtbuscar.getText());
        controller = new UsuarioJpaController();
    }
    private final String accion="guardar";
    
    private void inhabilitar(){
        txtidusuario.setVisible(false);
        txtlogin.setEnabled(false);
        txtpassword.setEnabled(false);
        dcfecha_ingreso.setEnabled(false);
        cboacceso1.setEnabled(false);
        cboestado.setEnabled(false);

        
        btnnuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btncancelar.setEnabled(false);
        btneliminar.setEnabled(false);
        btneditar.setEnabled(false);
        
    }
    
    void habilitar(){
       txtidusuario.setVisible(true);
        txtlogin.setEnabled(true);
        txtpassword.setEnabled(true);
        dcfecha_ingreso.setEnabled(true);
        cboacceso1.setEnabled(true);
        cboestado.setEnabled(true);

        
        btnnuevo.setEnabled(false);
        btnguardar.setEnabled(true);
        btncancelar.setEnabled(true);
        btneliminar.setEnabled(true);
        btneditar.setEnabled(true);
        
        txtidusuario.setText("");
        txtlogin.setText("");
        txtpassword.setText("");
         
         
      cboestado.setSelectedItem("<Seleccionar>");
       cboacceso1.setSelectedItem("<Seleccionar>");
        txtlogin.requestFocus();
    }
    
    void limpiar(){
        
        txtidusuario.setText("");
        txtlogin.setText("");
        txtpassword.setText("");
        cboestado.setSelectedItem("<Seleccionar>");
        cboacceso1.setSelectedItem("<Seleccionar>");
        txtlogin.requestFocus();
    
    }
   
    private void mostrar(String buscar)  {
         try {
             System.out.println("buscar = " + buscar);
         String[] titulos = {"Codigo","Nombre", "Usuario", "Acceso", "Estado", "Fecha_Ingreso"};
         DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            List<Usuario> users = controller.search(buscar);
             System.out.println("users = " + users);
             String[] usuarios = new String[6];
             users.forEach((user) -> {
                 System.out.println("user = " + user);
                 usuarios[0] = user.getId().toString();
                 usuarios[1] = user.getNombre();
                 usuarios[2] = user.getUsername();
                 usuarios[3] = user.getAcceso();
                 usuarios[4] = user.getEstado();
                 usuarios[5] = user.getFechaIngreso().toString();
                
                 modelo.addRow(usuarios);
             });
             
             tablausuario.setModel(modelo);
             lbltotalregistros.setText("Total Registros: "+Integer.toString(controller.getUsuarioCount()));
         } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, "error al cargar tabla de usuarios");
              System.out.println("error = " + e.getMessage());
         }
    }   
    
   //Funcion para llenar el jcombobox en el formulario frmusuarios

    private void llenacombo() throws NullPointerException { 
        try{
        List<Usuario> users = controller.findUsuarioEntities(); 
        System.out.println("users = " + users);
        if (users.isEmpty()) {
            System.out.println("Error on llenacombo return users null");
        }   
        users.forEach((user) -> {
          comboModelo.addElement(user);
        });
        controller.close();
        }catch(NullPointerException e){
            System.out.println("error = " + e.getMessage());
            JOptionPane.showMessageDialog(this,"Error al cargar el lista de usuarios");
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

        txtidusuario = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        cboacceso1 = new javax.swing.JComboBox();
        cboestado = new javax.swing.JComboBox();
        btnguardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        dcfecha_ingreso = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablausuario = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        btnsalir = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        lbltotalregistros = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        btnbuscar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        txtidusuario.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO DE DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS PGothic", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Usuario:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, 29));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, 29));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Acceso:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, 29));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Estado:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, 29));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fec_Ingreso:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 345, -1, 29));

        txtlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloginActionPerformed(evt);
            }
        });
        jPanel2.add(txtlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 114, -1));

        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 156, 128, -1));

        cboacceso1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccionar>", "Administrador", "Visitante" }));
        cboacceso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboacceso1ActionPerformed(evt);
            }
        });
        jPanel2.add(cboacceso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        cboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccionar>", "A", "D" }));
        cboestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboestadoActionPerformed(evt);
            }
        });
        jPanel2.add(cboestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        btnguardar.setBackground(new java.awt.Color(255, 255, 255));
        btnguardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(51, 51, 51));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_norm.png"))); // NOI18N
        btnguardar.setBorderPainted(false);
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnguardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_press.png"))); // NOI18N
        btnguardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_roll.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 453, -1, -1));

        btncancelar.setBackground(new java.awt.Color(255, 255, 255));
        btncancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_norm.png"))); // NOI18N
        btncancelar.setBorderPainted(false);
        btncancelar.setContentAreaFilled(false);
        btncancelar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_press.png"))); // NOI18N
        btncancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_roll.png"))); // NOI18N
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 453, -1, -1));

        btnnuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnnuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_norm.png"))); // NOI18N
        btnnuevo.setBorderPainted(false);
        btnnuevo.setContentAreaFilled(false);
        btnnuevo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_press.png"))); // NOI18N
        btnnuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_roll.png"))); // NOI18N
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 453, 129, -1));
        jPanel2.add(dcfecha_ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, -1));

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE USUARIOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS PGothic", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

        tablausuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablausuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablausuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablausuario);

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Agency FB", 1, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir_norm.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.setBorderPainted(false);
        btnsalir.setContentAreaFilled(false);
        btnsalir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir_press.png"))); // NOI18N
        btnsalir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir_roll.png"))); // NOI18N

        btneliminar.setBackground(new java.awt.Color(255, 255, 255));
        btneliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_norm.png"))); // NOI18N
        btneliminar.setBorderPainted(false);
        btneliminar.setContentAreaFilled(false);
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btneliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_press.png"))); // NOI18N
        btneliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_roll.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        lbltotalregistros.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        lbltotalregistros.setForeground(new java.awt.Color(255, 255, 255));
        lbltotalregistros.setText("Registros:");

        btneditar.setBackground(new java.awt.Color(255, 255, 255));
        btneditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_norm.png"))); // NOI18N
        btneditar.setBorderPainted(false);
        btneditar.setContentAreaFilled(false);
        btneditar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_press.png"))); // NOI18N
        btneditar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_roll.png"))); // NOI18N
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnbuscar1.setBackground(new java.awt.Color(51, 51, 255));
        btnbuscar1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        btnbuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar1.setText("Mostrar Todos");
        btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BUSCAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btneliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(32, 32, 32)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lbltotalregistros))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(26, 26, 26)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(btnbuscar1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar1)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btneliminar)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbltotalregistros))
                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloginActionPerformed
        // TODO add your handling code here:
        txtlogin.transferFocus();
    }//GEN-LAST:event_txtloginActionPerformed

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
        txtpassword.transferFocus();
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void cboacceso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboacceso1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboacceso1ActionPerformed

    private void cboestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboestadoActionPerformed
        // TODO add your handling code here:
        cboestado.transferFocus();

    }//GEN-LAST:event_cboestadoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         if (txtlogin.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el login");
            txtlogin.requestFocus();
            return;
        }

        if (txtpassword.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el password");
            txtpassword.requestFocus();
            return;
        }

        try {
            Usuario dts =controller.findUsuario(Integer.parseInt(txtidusuario.getText()));
            dts.setUsername(txtlogin.getText());
            String pass = txtpassword.getText().toString();
            dts.setPass(pass);
            int seleccionado = cboacceso1.getSelectedIndex();
            dts.setAcceso((String) cboacceso1.getItemAt(seleccionado));
            int seleccionEstado = cboestado.getSelectedIndex();
            dts.setEstado((String) cboestado.getItemAt(seleccionEstado));
   
            Calendar cal;
            int d, m, a;
            cal = dcfecha_ingreso.getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;
            dts.setFechaIngreso(new Date(a, m, d));
            dts.setNombre(txtlogin.getText());
            controller.edit(dts);
            JOptionPane.showConfirmDialog(rootPane, "Usuario actualizado satisfactoriamente");
            mostrar("");
            limpiar();
            inhabilitar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el registro");
            System.out.println("error on save user = " + e.getMessage());
        }

    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

        if (txtlogin.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el login");
            txtlogin.requestFocus();
            return;
        }

        if (txtpassword.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el password");
            txtpassword.requestFocus();
            return;
        }

        try {
            Usuario dts = new Usuario();
            dts.setUsername(txtlogin.getText());
            String pass = txtpassword.getText().toString();
            dts.setPass(pass);
            int seleccionado = cboacceso1.getSelectedIndex();
            dts.setAcceso((String) cboacceso1.getItemAt(seleccionado));
            int seleccionEstado = cboestado.getSelectedIndex();
            dts.setEstado((String) cboestado.getItemAt(seleccionEstado));

            Calendar cal;
            int d, m, a;
            cal = dcfecha_ingreso.getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;
            dts.setFechaIngreso(new Date(a, m, d));
            dts.setNombre(txtlogin.getText());
            controller.create(dts);
            JOptionPane.showConfirmDialog(rootPane, "Usuario registrado satisfactoriamente");
            mostrar("");
            limpiar();
            inhabilitar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el registro");
            System.out.println("error on save user = " + e.getMessage());
        }

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO9 add your handling code here:
        limpiar();
        inhabilitar();

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:

        if (!txtidusuario.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de Eliminar el Usuario", "Confirmar", 2);

            try {
                if (confirmacion == 0) {

                    Usuario user;
                    user = controller.findUsuario(Integer.parseInt(txtidusuario.getText()));
                    controller.destroy(user.getId());
                    mostrar("");
                    inhabilitar();
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(frmusuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        habilitar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void tablausuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablausuarioMouseClicked
        // TODO add your handling code here:
        int fila =tablausuario.rowAtPoint(evt.getPoint());
          
        txtidusuario.setText(tablausuario.getValueAt(fila, 0).toString());
        txtlogin.setText(tablausuario.getValueAt(fila, 2).toString());
        txtpassword.setText(tablausuario.getValueAt(fila, 3).toString());
        cboacceso1.setSelectedItem(tablausuario.getValueAt(fila,4).toString());
        cboestado.setSelectedItem(tablausuario.getValueAt(fila, 5).toString());
       // dcfecha_ingreso.setDate(Date.valueOf(tablausuario.getValueAt(fila, 5).toString()));
    }//GEN-LAST:event_tablausuarioMouseClicked

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        // TODO add your handling code here:
        mostrar("");
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        // TODO add your handling code here:
            mostrar(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cboacceso1;
    private javax.swing.JComboBox cboestado;
    private com.toedter.calendar.JDateChooser dcfecha_ingreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbltotalregistros;
    private javax.swing.JTable tablausuario;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtidusuario;
    private javax.swing.JTextField txtlogin;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
