/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ClienteJpaController;
import controladores.MarcaJpaController;
import controladores.ProductoJpaController;
import controladores.UsuarioJpaController;
import controladores.exceptions.NonexistentEntityException;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import rojeru_san.RSPanelsSlider;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.Marca;
import modelo.Producto;

/**
 *
 * @author matias
 */
public class inicio extends javax.swing.JFrame {
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    private DefaultComboBoxModel comboModelo;
       UsuarioJpaController Usercontroller;
       ClienteJpaController clienteController;
       MarcaJpaController marcasController;
       ProductoJpaController productoController;
    /**
     * Creates new form inicio
     */
    public inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setExtendedState(frminicio.MAXIMIZED_BOTH);
        this.setTitle("MENU DEL SISTEMA");
      h1 = new Thread();
      h1.start();
      
     Usercontroller = new UsuarioJpaController();
     clienteController = new ClienteJpaController();
     marcasController = new MarcaJpaController();
     productoController =new ProductoJpaController();
    }
    private final String accion="guardar";

        //usuarios
    private void inhabilitarUsuarios(){
        txtidusuario.setVisible(false);
        txtlogin.setEnabled(false);
        txtpassword.setEnabled(false);
        dcfecha_ingreso.setEnabled(false);
        cboacceso1.setEnabled(false);
        cboestado.setEnabled(false);

        
        btnnuevoUsuarios.setEnabled(true);
        btnguardarUsuarios.setEnabled(false);
        btncancelarUsuarios.setEnabled(false);
        btneliminarUsuarios.setEnabled(false);
        btneditarUsuarios.setEnabled(false);
        
    }
    
    void habilitar(){
       txtidusuario.setVisible(true);
        txtlogin.setEnabled(true);
        txtpassword.setEnabled(true);
        dcfecha_ingreso.setEnabled(true);
        cboacceso1.setEnabled(true);
        cboestado.setEnabled(true);

        
        btnnuevoUsuarios.setEnabled(false);
        btnguardarUsuarios.setEnabled(true);
        btncancelarUsuarios.setEnabled(true);
        btneliminarUsuarios.setEnabled(true);
        btneditarUsuarios.setEnabled(true);
        
        txtidusuario.setText("");
        txtlogin.setText("");
        txtpassword.setText("");
         
         
      cboestado.setSelectedItem("<Seleccionar>");
       cboacceso1.setSelectedItem("<Seleccionar>");
        txtlogin.requestFocus();
    }
    
    void limpiarUsuarios(){
        
        txtidusuario.setText("");
        txtlogin.setText("");
        txtpassword.setText("");
        cboestado.setSelectedItem("<Seleccionar>");
        cboacceso1.setSelectedItem("<Seleccionar>");
        txtlogin.requestFocus();
    
    }
   
    private void mostrarUsuarios(String buscar)  {
         try {
             System.out.println("buscar = " + buscar);
         String[] titulos = {"Codigo","Nombre", "Usuario", "Acceso", "Estado", "Fecha_Ingreso"};
         DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            List<Usuario> users = Usercontroller.search(buscar);
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
             lbltotalregistros.setText("Total Registros: "+Integer.toString(Usercontroller.getUsuarioCount()));
         } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, "error al cargar tabla de usuarios");
              System.out.println("error = " + e.getMessage());
         }
    }
    
    private void llenacombo() throws NullPointerException { 
        try{
        List<Usuario> users = Usercontroller.findUsuarioEntities(); 
        System.out.println("users = " + users);
        if (users.isEmpty()) {
            System.out.println("Error on llenacombo return users null");
        }   
        users.forEach((user) -> {
          comboModelo.addElement(user);
        });
        Usercontroller.close();
        }catch(NullPointerException e){
            System.out.println("error = " + e.getMessage());
            JOptionPane.showMessageDialog(this,"Error al cargar el lista de usuarios");
        }
    }
    
    //clientes
    private void mostrarClientes(String buscar)  {
         try {
         String[] titulos = {"Codigo","Nombre", "Email", "Ruc", "Cedula", "Estado"};
         DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            List<Cliente> clients = clienteController.search(buscar);
             String[] clientes = new String[6];
             clients.forEach((cliente) -> {
                 clientes[0] = cliente.getId().toString();
                 clientes[1] = cliente.getNombre();
                 clientes[2] = cliente.getEmail();
                 clientes[3] = cliente.getRuc();
                 clientes[4] = cliente.getCedula().toString();
                 clientes[5] = cliente.getEstado();
                
                 modelo.addRow(clientes);
             });
             
             tbclientes.setModel(modelo);
//             lbltotalregistros.setText("Total Registros: "+Integer.toString(Usercontroller.getUsuarioCount()));
         } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, "error al cargar tabla de usuarios");
              System.out.println("error = " + e.getMessage());
         }
    }
      
    void bloquearClientes(){
    txtidcliente.setEnabled(false);
    txtnom.setEnabled(false);
    txtape.setEnabled(false);
    txtdir.setEnabled(false);
    txtemail.setEnabled(false);
    txttel.setEnabled(false);
    txtruc.setEnabled(false);
    txtdni.setEnabled(false);
    btnguardarClientes.setEnabled(false);
    btnnuevoClientes.setEnabled(true);
    btncancelarClientes.setEnabled(false);
    btnactualizarClientes.setEnabled(false);
    }
     
    void desbloquearClientes(){
    txtidcliente.setEnabled(true);
    txtnom.setEnabled(true);
    txtape.setEnabled(true);
    txtdir.setEnabled(true);
    txtemail.setEnabled(true);
    txttel.setEnabled(true);
    txtruc.setEnabled(true);
    txtdni.setEnabled(true);
    btnguardarClientes.setEnabled(true);
    btnnuevoClientes.setEnabled(false);
    btncancelarClientes.setEnabled(true);
    btnactualizarClientes.setEnabled(true);
    
    }
     
    void limpiarClientes(){
    txtidcliente.setText("");
    txtnom.setText("");
    txtdir.setText("");
    txtdni.setText("");
    txtemail.setText("");
    txtruc.setText("");
    txttel.setText("");
    txtape.setText("");
     }

    //marca
    void bloquearMarca(){
    
    txtnombremarca.setEnabled(false);
    txtdescripcionmarca.setEnabled(false);
    btnguardarMarca.setEnabled(false);
    btnnuevoMarca.setEnabled(true);
    btneditarMarca.setEnabled(false);
    btneliminarMarca.setEnabled(false);
    btncancelarMarca.setEnabled(false);
    }
     
    void desbloquearMarca(){
    
    txtnombremarca.setEnabled(true);
    txtdescripcionmarca.setEnabled(true);
    btnguardarMarca.setEnabled(true);
    btnnuevoMarca.setEnabled(false);
    btneliminarMarca.setEnabled(true);
    btneditarMarca.setEnabled(true);
    btncancelarMarca.setEnabled(true);
    
    }
    
    void desbloquearclikedMarca(){
    
    txtnombremarca.setEnabled(true);
    txtdescripcionmarca.setEnabled(true);
    btnguardarMarca.setEnabled(true);
    btnnuevoMarca.setEnabled(true);
    btneliminarMarca.setEnabled(true);
    btncancelarMarca.setEnabled(true);
    
    }
     
    void limpiarMarca(){
    txtnombremarca.setText("");
    txtdescripcionmarca.setText("");
    }
   
    void mostrarProductos(String buscar)  {
         try {
         String[] titulos = {"Codigo","Marca", "Proveedor","Descriptcion","Modelo","Stock","Precio"};
         DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            List<Producto> products = productoController.search(buscar);
             String[] productos = new String[7];
             products.forEach((producto) -> {
                 productos[0] = producto.getId().toString();
                 productos[1] = producto.getMarcaId().getDescripcion();
                 productos[2] = producto.getProveedorId().getDescripcion();
                 productos[3] = producto.getNombre();
                 productos[4] = producto.getModelo();
                 productos[5] = producto.getCantidad().toString();
                 productos[6] = producto.getPrecio().toString();
                 
                modelo.addRow(productos);
             });
             
             tbproducto.setModel(modelo);
//             lbltotalregistros.setText("Total Registros: "+Integer.toString(Usercontroller.getUsuarioCount()));
         } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, "error al cargar tabla de usuarios");
              System.out.println("error = " + e.getMessage());
         }
    }

    
    //productos
    void bloquearProducto(){
    
    txtproducto.setEnabled(false);
    txtmodelo.setEnabled(false);
    txtcantidad.setEnabled(false);
    cbomarca.setEnabled(false);
    txtprecio.setEnabled(false);
    txtmodelo.setEnabled(false);
    btnnuevoProducto.setEnabled(true);
    btnguardarProducto.setEnabled(false);
    btneditarProducto.setEnabled(false);
    btneliminarProducto.setEnabled(false);
    btncancelarProducto.setEnabled(false);
    }
     
    void desbloquearProducto(){
    
    txtproducto.setEnabled(true);
    txtmodelo.setEnabled(true);
    txtcantidad.setEnabled(true);
    cbomarca.setEnabled(true);
    txtprecio.setEnabled(true);
    txtmodelo.setEnabled(true);
    btnnuevoProducto.setEnabled(false);
    btnguardarProducto.setEnabled(true);
    btneditarProducto.setEnabled(true);
    btneliminarProducto.setEnabled(true);
    btncancelarProducto.setEnabled(true);
    
    }
     
    void desbloquearclikedProducto(){
    
   txtproducto.setEnabled(true);
    txtmodelo.setEnabled(true);
    txtcantidad.setEnabled(true);
    cbomarca.setEnabled(true);
    txtmodelo.setEnabled(true);
    btnnuevoProducto.setEnabled(true);
    btnnuevoProducto.setEnabled(true);
    btneditarProducto.setEnabled(true);
    btneliminarProducto.setEnabled(true);
    btncancelarProducto.setEnabled(true);
    }
     
    void limpiarProducto(){
    txtproducto.setText("");
    txtmodelo.setText("");
    txtcantidad.setText(""); 
    //cbomarca.setText("");
    txtmodelo.setText("");
    }
    
    void mostrarMarcas(String buscar)  {
         try {
         String[] titulos = {"Codigo","Marca", "Descripcion"};
         DefaultTableModel modelo = new DefaultTableModel(null, titulos);
            List<Marca> marks = marcasController.search(buscar);
             String[] marcas = new String[3];
             marks.forEach((marca) -> {
                 marcas[0] = marca.getId().toString();
                 marcas[1] = marca.getMarca();
                 marcas[2] = marca.getDescripcion();
                
                 modelo.addRow(marcas);
             });
             
             tbmarca.setModel(modelo);
//             lbltotalregistros.setText("Total Registros: "+Integer.toString(Usercontroller.getUsuarioCount()));
         } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, "error al cargar tabla de usuarios");
              System.out.println("error = " + e.getMessage());
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
        evaluatorLine2D1 = new org.jdesktop.swing.animation.timing.evaluators.EvaluatorLine2D();
        jPanel1 = new javax.swing.JPanel();
        sidebar = new javax.swing.JPanel();
        lblacceso = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnUsers = new rojeru_san.RSButton();
        btnClientes = new rojeru_san.RSButton();
        btnProductos = new rojeru_san.RSButton();
        btnProve = new rojeru_san.RSButton();
        btnRepor = new rojeru_san.RSButton();
        btnVenta = new rojeru_san.RSButton();
        jPanel2 = new javax.swing.JPanel();
        lblhora = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojeru_san.RSPanelsSlider();
        pnelUsuario = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtlogin = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        cboacceso1 = new javax.swing.JComboBox();
        cboestado = new javax.swing.JComboBox();
        btnguardarUsuarios = new javax.swing.JButton();
        btncancelarUsuarios = new javax.swing.JButton();
        btnnuevoUsuarios = new javax.swing.JButton();
        dcfecha_ingreso = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablausuario = new javax.swing.JTable();
        txtbuscarUsuarios = new javax.swing.JTextField();
        btneliminarUsuarios = new javax.swing.JButton();
        lbltotalregistros = new javax.swing.JLabel();
        btneditarUsuarios = new javax.swing.JButton();
        btnbuscar1Usuarios = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnelClientes = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtidcliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtape = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtdir = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnnuevoClientes = new javax.swing.JButton();
        btnguardarClientes = new javax.swing.JButton();
        btnactualizarClientes = new javax.swing.JButton();
        btncancelarClientes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        txtbuscarClientes = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnbuscarClientes = new javax.swing.JButton();
        pnelMarca = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtidmarca = new javax.swing.JTextField();
        txtnombremarca = new javax.swing.JTextField();
        txtdescripcionmarca = new javax.swing.JTextField();
        btnnuevoMarca = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btncancelarMarca = new javax.swing.JButton();
        btnguardarMarca = new javax.swing.JButton();
        btneliminarMarca = new javax.swing.JButton();
        btneditarMarca = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbmarca = new javax.swing.JTable();
        txtbuscarMarca = new javax.swing.JTextField();
        btnmostrarMarca = new javax.swing.JButton();
        pnelProducto = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        Idproducto = new javax.swing.JTextField();
        txtproducto = new javax.swing.JTextField();
        txtmodelo = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        cbomarca = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        btnnuevoProducto = new javax.swing.JButton();
        btnguardarProducto = new javax.swing.JButton();
        btneditarProducto = new javax.swing.JButton();
        btneliminarProducto = new javax.swing.JButton();
        btncancelarProducto = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbproducto = new javax.swing.JTable();
        btnbuscarProducto = new javax.swing.JButton();
        txtbuscarProducto = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        pnelVenta = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        btnclientes = new javax.swing.JButton();
        txtruc1 = new javax.swing.JTextField();
        btnproductosFactura = new javax.swing.JButton();
        txtcod1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtdni1 = new javax.swing.JTextField();
        txtdir1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtfac = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtigv = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        tbdetalle = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbdet = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        btnguardarFactura = new javax.swing.JButton();
        btnsalir2 = new javax.swing.JButton();
        btncalcularFactura = new javax.swing.JButton();
        btneliminarFactura = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();

        txtidusuario.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidebar.setBackground(new java.awt.Color(73, 181, 172));
        sidebar.setToolTipText("");
        sidebar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblacceso.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        lblacceso.setForeground(new java.awt.Color(255, 255, 255));
        lblacceso.setText("MUESTRA TIPO ACCESO");
        sidebar.add(lblacceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 710, 200, -1));

        lblnombre.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(255, 255, 255));
        lblnombre.setText("MUESTRA NOMBRE");
        sidebar.add(lblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 674, 230, 30));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario (2).png"))); // NOI18N
        sidebar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 669, -1, 72));

        btnUsers.setBackground(new java.awt.Color(73, 181, 172));
        btnUsers.setBorder(null);
        btnUsers.setText("Usuarios");
        btnUsers.setBorderPainted(false);
        btnUsers.setColorHover(new java.awt.Color(0, 153, 153));
        btnUsers.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });
        sidebar.add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 330, 50));

        btnClientes.setBackground(new java.awt.Color(73, 181, 172));
        btnClientes.setBorder(null);
        btnClientes.setText("Clientes");
        btnClientes.setColorHover(new java.awt.Color(0, 153, 153));
        btnClientes.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        sidebar.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 330, 50));

        btnProductos.setBackground(new java.awt.Color(73, 181, 172));
        btnProductos.setBorder(null);
        btnProductos.setText("Productos");
        btnProductos.setColorHover(new java.awt.Color(0, 153, 153));
        btnProductos.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        sidebar.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 330, 50));

        btnProve.setBackground(new java.awt.Color(73, 181, 172));
        btnProve.setBorder(null);
        btnProve.setText("Proveedores");
        btnProve.setColorHover(new java.awt.Color(0, 153, 153));
        btnProve.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnProve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveActionPerformed(evt);
            }
        });
        sidebar.add(btnProve, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 330, 50));

        btnRepor.setBackground(new java.awt.Color(73, 181, 172));
        btnRepor.setBorder(null);
        btnRepor.setText("Reportes");
        btnRepor.setColorHover(new java.awt.Color(0, 153, 153));
        btnRepor.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnRepor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporActionPerformed(evt);
            }
        });
        sidebar.add(btnRepor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 330, 50));

        btnVenta.setBackground(new java.awt.Color(73, 181, 172));
        btnVenta.setBorder(null);
        btnVenta.setText("Generar Venta");
        btnVenta.setColorHover(new java.awt.Color(0, 153, 153));
        btnVenta.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        btnVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaActionPerformed(evt);
            }
        });
        sidebar.add(btnVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 330, 50));

        jPanel1.add(sidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 760));

        jPanel2.setBackground(new java.awt.Color(33, 45, 62));
        jPanel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblhora.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        lblhora.setForeground(new java.awt.Color(255, 255, 255));
        lblhora.setText("Muestra hora");
        jPanel2.add(lblhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 670, 280, 90));

        rSPanelsSlider1.setBackground(new java.awt.Color(33, 45, 62));

        pnelUsuario.setBackground(new java.awt.Color(33, 45, 62));
        pnelUsuario.setName("pnelUsuario"); // NOI18N
        pnelUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(33, 45, 62));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRO DE DATOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS PGothic", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Usuario:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, 29));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, 29));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Acceso:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, 29));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Estado:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, 29));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Fec_Ingreso:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 345, -1, 29));

        txtlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloginActionPerformed(evt);
            }
        });
        jPanel4.add(txtlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 114, -1));

        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        jPanel4.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 156, 128, -1));

        cboacceso1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccionar>", "Administrador", "Visitante" }));
        cboacceso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboacceso1ActionPerformed(evt);
            }
        });
        jPanel4.add(cboacceso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        cboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccionar>", "A", "D" }));
        cboestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboestadoActionPerformed(evt);
            }
        });
        jPanel4.add(cboestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        btnguardarUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btnguardarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnguardarUsuarios.setForeground(new java.awt.Color(51, 51, 51));
        btnguardarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_norm.png"))); // NOI18N
        btnguardarUsuarios.setBorderPainted(false);
        btnguardarUsuarios.setContentAreaFilled(false);
        btnguardarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnguardarUsuarios.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_press.png"))); // NOI18N
        btnguardarUsuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_roll.png"))); // NOI18N
        btnguardarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarUsuariosActionPerformed(evt);
            }
        });
        jPanel4.add(btnguardarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 453, -1, -1));

        btncancelarUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btncancelarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btncancelarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_norm.png"))); // NOI18N
        btncancelarUsuarios.setBorderPainted(false);
        btncancelarUsuarios.setContentAreaFilled(false);
        btncancelarUsuarios.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_press.png"))); // NOI18N
        btncancelarUsuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_roll.png"))); // NOI18N
        btncancelarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarUsuariosActionPerformed(evt);
            }
        });
        jPanel4.add(btncancelarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, -1, -1));

        btnnuevoUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btnnuevoUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnnuevoUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_norm.png"))); // NOI18N
        btnnuevoUsuarios.setBorderPainted(false);
        btnnuevoUsuarios.setContentAreaFilled(false);
        btnnuevoUsuarios.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_press.png"))); // NOI18N
        btnnuevoUsuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_roll.png"))); // NOI18N
        btnnuevoUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoUsuariosActionPerformed(evt);
            }
        });
        jPanel4.add(btnnuevoUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 453, 129, -1));
        jPanel4.add(dcfecha_ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 31, 500, 537));

        jPanel5.setBackground(new java.awt.Color(51, 102, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE USUARIOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS PGothic", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setForeground(new java.awt.Color(102, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 176, 650, 172));

        txtbuscarUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarUsuariosKeyReleased(evt);
            }
        });
        jPanel5.add(txtbuscarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 117, 333, 30));

        btneliminarUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btneliminarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btneliminarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_norm.png"))); // NOI18N
        btneliminarUsuarios.setBorderPainted(false);
        btneliminarUsuarios.setContentAreaFilled(false);
        btneliminarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btneliminarUsuarios.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_press.png"))); // NOI18N
        btneliminarUsuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_roll.png"))); // NOI18N
        btneliminarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarUsuariosActionPerformed(evt);
            }
        });
        jPanel5.add(btneliminarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 427, -1, -1));

        lbltotalregistros.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        lbltotalregistros.setForeground(new java.awt.Color(255, 255, 255));
        lbltotalregistros.setText("Registros:");
        jPanel5.add(lbltotalregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, -1, -1));

        btneditarUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btneditarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btneditarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_norm.png"))); // NOI18N
        btneditarUsuarios.setBorderPainted(false);
        btneditarUsuarios.setContentAreaFilled(false);
        btneditarUsuarios.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_press.png"))); // NOI18N
        btneditarUsuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_roll.png"))); // NOI18N
        btneditarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarUsuariosActionPerformed(evt);
            }
        });
        jPanel5.add(btneditarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 435, -1, 43));

        btnbuscar1Usuarios.setBackground(new java.awt.Color(51, 51, 255));
        btnbuscar1Usuarios.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        btnbuscar1Usuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar1Usuarios.setText("Mostrar Todos");
        btnbuscar1Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar1UsuariosActionPerformed(evt);
            }
        });
        jPanel5.add(btnbuscar1Usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BUSCAR");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 119, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 680, 537));

        pnelUsuario.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        rSPanelsSlider1.add(pnelUsuario, "card3");

        pnelClientes.setBackground(new java.awt.Color(33, 45, 62));
        pnelClientes.setName("pnelClientes"); // NOI18N
        pnelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(0, 102, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Codigo:");

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombres:");

        txtnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnomKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos:");

        txtape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapeKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DNI:");

        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });
        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdniKeyTyped(evt);
            }
        });

        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Telefono:");

        jLabel10.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Email:");

        txtdir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdirKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Direccion:");

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("RUC:");

        txtruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrucActionPerformed(evt);
            }
        });
        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrucKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(txtdni, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(78, 78, 78)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel15))
                                            .addComponent(jLabel7))
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtape, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pnelClientes.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 780, 240));

        jPanel7.setBackground(new java.awt.Color(0, 102, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnnuevoClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_norm.png"))); // NOI18N
        btnnuevoClientes.setBorder(null);
        btnnuevoClientes.setBorderPainted(false);
        btnnuevoClientes.setContentAreaFilled(false);
        btnnuevoClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnnuevoClientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_press.png"))); // NOI18N
        btnnuevoClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_roll.png"))); // NOI18N
        btnnuevoClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoClientesActionPerformed(evt);
            }
        });
        jPanel7.add(btnnuevoClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 32, 133, -1));

        btnguardarClientes.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        btnguardarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_norm.png"))); // NOI18N
        btnguardarClientes.setBorderPainted(false);
        btnguardarClientes.setContentAreaFilled(false);
        btnguardarClientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_press.png"))); // NOI18N
        btnguardarClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_roll.png"))); // NOI18N
        btnguardarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarClientesActionPerformed(evt);
            }
        });
        jPanel7.add(btnguardarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 86, -1, -1));

        btnactualizarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/upd_norm.png"))); // NOI18N
        btnactualizarClientes.setBorderPainted(false);
        btnactualizarClientes.setContentAreaFilled(false);
        btnactualizarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        btnactualizarClientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/upd_press.png"))); // NOI18N
        btnactualizarClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/upd_roll.png"))); // NOI18N
        btnactualizarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarClientesActionPerformed(evt);
            }
        });
        jPanel7.add(btnactualizarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 155, -1, -1));

        btncancelarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_norm.png"))); // NOI18N
        btncancelarClientes.setBorderPainted(false);
        btncancelarClientes.setContentAreaFilled(false);
        btncancelarClientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_press.png"))); // NOI18N
        btncancelarClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_roll.png"))); // NOI18N
        btncancelarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarClientesActionPerformed(evt);
            }
        });
        jPanel7.add(btncancelarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 212, -1, -1));

        pnelClientes.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 90, -1, 290));

        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbclientes);

        pnelClientes.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 930, -1));

        txtbuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarClientesActionPerformed(evt);
            }
        });
        txtbuscarClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarClientesKeyReleased(evt);
            }
        });
        pnelClientes.add(txtbuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 360, -1));

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("BUSCAR:");
        pnelClientes.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, -1, -1));

        btnbuscarClientes.setBackground(new java.awt.Color(51, 153, 255));
        btnbuscarClientes.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnbuscarClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscarClientes.setText("Mostrar Todos");
        btnbuscarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarClientesActionPerformed(evt);
            }
        });
        pnelClientes.add(btnbuscarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, -1, -1));

        rSPanelsSlider1.add(pnelClientes, "card2");

        pnelMarca.setBackground(new java.awt.Color(33, 45, 62));
        pnelMarca.setName("pnelMarca"); // NOI18N
        pnelMarca.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(0, 102, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DE MARCA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ID:");

        jLabel18.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("MARCA:");

        jLabel19.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("DESCRIPCION:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtdescripcionmarca, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addComponent(txtnombremarca))
                    .addComponent(txtidmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtnombremarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtdescripcionmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pnelMarca.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 670, 180));

        btnnuevoMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnnuevoMarca.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        btnnuevoMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_norm.png"))); // NOI18N
        btnnuevoMarca.setBorderPainted(false);
        btnnuevoMarca.setContentAreaFilled(false);
        btnnuevoMarca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_press.png"))); // NOI18N
        btnnuevoMarca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_roll.png"))); // NOI18N
        btnnuevoMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoMarcaActionPerformed(evt);
            }
        });
        pnelMarca.add(btnnuevoMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel10.setBackground(new java.awt.Color(0, 102, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        btncancelarMarca.setBackground(new java.awt.Color(255, 255, 255));
        btncancelarMarca.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        btncancelarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_norm.png"))); // NOI18N
        btncancelarMarca.setBorderPainted(false);
        btncancelarMarca.setContentAreaFilled(false);
        btncancelarMarca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_press.png"))); // NOI18N
        btncancelarMarca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_roll.png"))); // NOI18N
        btncancelarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarMarcaActionPerformed(evt);
            }
        });

        btnguardarMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnguardarMarca.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        btnguardarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_norm.png"))); // NOI18N
        btnguardarMarca.setBorderPainted(false);
        btnguardarMarca.setContentAreaFilled(false);
        btnguardarMarca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_press.png"))); // NOI18N
        btnguardarMarca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_roll.png"))); // NOI18N
        btnguardarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarMarcaActionPerformed(evt);
            }
        });

        btneliminarMarca.setBackground(new java.awt.Color(255, 255, 255));
        btneliminarMarca.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        btneliminarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_norm.png"))); // NOI18N
        btneliminarMarca.setBorderPainted(false);
        btneliminarMarca.setContentAreaFilled(false);
        btneliminarMarca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_press.png"))); // NOI18N
        btneliminarMarca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_roll.png"))); // NOI18N
        btneliminarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarMarcaActionPerformed(evt);
            }
        });

        btneditarMarca.setBackground(new java.awt.Color(255, 255, 255));
        btneditarMarca.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        btneditarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_norm.png"))); // NOI18N
        btneditarMarca.setBorderPainted(false);
        btneditarMarca.setContentAreaFilled(false);
        btneditarMarca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_press.png"))); // NOI18N
        btneditarMarca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_roll.png"))); // NOI18N
        btneditarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarMarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btneliminarMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnguardarMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneditarMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btncancelarMarca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btnguardarMarca)
                .addGap(18, 18, 18)
                .addComponent(btneliminarMarca)
                .addGap(28, 28, 28)
                .addComponent(btneditarMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncancelarMarca)
                .addGap(29, 29, 29))
        );

        pnelMarca.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, -1, 421));

        tbmarca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbmarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmarcaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbmarca);

        pnelMarca.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 760, 392));

        txtbuscarMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarMarcaKeyReleased(evt);
            }
        });
        pnelMarca.add(txtbuscarMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 496, -1));

        btnmostrarMarca.setBackground(new java.awt.Color(0, 153, 255));
        btnmostrarMarca.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnmostrarMarca.setForeground(new java.awt.Color(255, 255, 255));
        btnmostrarMarca.setText("Mostrar todo");
        btnmostrarMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarMarcaActionPerformed(evt);
            }
        });
        pnelMarca.add(btnmostrarMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, -1, -1));

        rSPanelsSlider1.add(pnelMarca, "card4");

        pnelProducto.setBackground(new java.awt.Color(33, 45, 62));
        pnelProducto.setDoubleBuffered(false);
        pnelProducto.setName("pnelProducto"); // NOI18N
        pnelProducto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(0, 102, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL PRODUCTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("ID:");

        jLabel21.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Producto:");

        jLabel22.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Modelo:");

        jLabel23.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cantidad:");

        jLabel24.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Precio:");

        jLabel25.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Marca:");

        txtproducto.setFont(new java.awt.Font("Rockwell Condensed", 0, 14)); // NOI18N

        txtmodelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmodeloActionPerformed(evt);
            }
        });

        cbomarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbomarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomarcaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(82, 82, 82)
                        .addComponent(Idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21))
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtproducto)
                            .addComponent(txtmodelo)
                            .addComponent(txtcantidad)
                            .addComponent(txtprecio)
                            .addComponent(cbomarca, 0, 191, Short.MAX_VALUE)))
                    .addComponent(jLabel25))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(Idproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbomarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
        );

        pnelProducto.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 580, 460));

        jPanel12.setBackground(new java.awt.Color(0, 102, 204));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        btnnuevoProducto.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnnuevoProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnnuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_norm.png"))); // NOI18N
        btnnuevoProducto.setBorderPainted(false);
        btnnuevoProducto.setContentAreaFilled(false);
        btnnuevoProducto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_press.png"))); // NOI18N
        btnnuevoProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo_roll.png"))); // NOI18N
        btnnuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoProductoActionPerformed(evt);
            }
        });

        btnguardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_norm.png"))); // NOI18N
        btnguardarProducto.setBorderPainted(false);
        btnguardarProducto.setContentAreaFilled(false);
        btnguardarProducto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_press.png"))); // NOI18N
        btnguardarProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save_roll.png"))); // NOI18N
        btnguardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarProductoActionPerformed(evt);
            }
        });

        btneditarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_norm.png"))); // NOI18N
        btneditarProducto.setBorderPainted(false);
        btneditarProducto.setContentAreaFilled(false);
        btneditarProducto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_press.png"))); // NOI18N
        btneditarProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_roll.png"))); // NOI18N
        btneditarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarProductoActionPerformed(evt);
            }
        });

        btneliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_norm.png"))); // NOI18N
        btneliminarProducto.setBorderPainted(false);
        btneliminarProducto.setContentAreaFilled(false);
        btneliminarProducto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_press.png"))); // NOI18N
        btneliminarProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_roll.png"))); // NOI18N
        btneliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarProductoActionPerformed(evt);
            }
        });

        btncancelarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_norm.png"))); // NOI18N
        btncancelarProducto.setBorderPainted(false);
        btncancelarProducto.setContentAreaFilled(false);
        btncancelarProducto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_press.png"))); // NOI18N
        btncancelarProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canc_roll.png"))); // NOI18N
        btncancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnguardarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnuevoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneditarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnnuevoProducto)
                .addGap(32, 32, 32)
                .addComponent(btnguardarProducto)
                .addGap(34, 34, 34)
                .addComponent(btneliminarProducto)
                .addGap(38, 38, 38)
                .addComponent(btneditarProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btncancelarProducto)
                .addContainerGap())
        );

        pnelProducto.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, 170, -1));

        tbproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductoMouseClicked(evt);
            }
        });
        tbproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbproductoKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbproducto);

        pnelProducto.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 560, 810, 160));

        btnbuscarProducto.setBackground(new java.awt.Color(51, 153, 255));
        btnbuscarProducto.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnbuscarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscarProducto.setText("Mostrar Todos");
        btnbuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarProductoActionPerformed(evt);
            }
        });
        pnelProducto.add(btnbuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 510, -1, -1));

        txtbuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarProductoActionPerformed(evt);
            }
        });
        txtbuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarProductoKeyReleased(evt);
            }
        });
        pnelProducto.add(txtbuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 520, 300, -1));

        jLabel26.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("BUSCAR:");
        pnelProducto.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 520, 110, -1));

        rSPanelsSlider1.add(pnelProducto, "card5");

        pnelVenta.setName("pnelVenta"); // NOI18N
        pnelVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnelVenta.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 318, -1, -1));

        jPanel13.setBackground(new java.awt.Color(0, 102, 255));
        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setForeground(new java.awt.Color(0, 0, 204));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("DE: BOUTIQUE TIA ELVA");
        jPanel13.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 29, -1, 14));

        jLabel29.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Av. Luis Alberto del parana. c/ bejarano");
        jPanel13.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 55, -1, -1));

        jLabel30.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Telf: 0981675321");
        jPanel13.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 55, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono de formulario usuario/icons8-camiseta-forester-64.png"))); // NOI18N
        jPanel13.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 9, 83, 92));

        jLabel32.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Asuncion - Paraguay");
        jPanel13.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 84, -1, -1));

        pnelVenta.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 700, -1));

        jPanel14.setBackground(new java.awt.Color(51, 102, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel14.setForeground(new java.awt.Color(102, 102, 255));

        jLabel33.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Señor(a):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setForeground(new java.awt.Color(0, 51, 204));
        txtnomape.setEnabled(false);
        txtnomape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomapeActionPerformed(evt);
            }
        });

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario (1).png"))); // NOI18N
        btnclientes.setToolTipText("Buscar cliente");
        btnclientes.setContentAreaFilled(false);
        btnclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        txtruc1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtruc1.setEnabled(false);

        btnproductosFactura.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        btnproductosFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnproductosFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.jpg"))); // NOI18N
        btnproductosFactura.setText("Buscar");
        btnproductosFactura.setBorderPainted(false);
        btnproductosFactura.setContentAreaFilled(false);
        btnproductosFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnproductosFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosFacturaActionPerformed(evt);
            }
        });

        txtcod1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod1.setEnabled(false);

        jLabel34.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Cod. Cliente:");

        txtdni1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdni1.setForeground(new java.awt.Color(51, 51, 255));
        txtdni1.setEnabled(false);

        txtdir1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdir1.setEnabled(false);

        jLabel35.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Direccion:");

        jLabel36.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("DNI:");

        jLabel37.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("RUC:");

        jLabel38.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Fecha:");

        jLabel39.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Articulo:");

        jPanel15.setBackground(new java.awt.Color(0, 153, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel40.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("FACTURA DE VENTA");

        jLabel41.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("RUC 10046495581");

        jLabel42.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Nº");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel42)
                        .addGap(18, 18, 18)
                        .addComponent(txtfac))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40))))
                .addContainerGap(36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(11, 11, 11)
                .addComponent(jLabel40)
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtfac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtdni1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcod1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtdir1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnproductosFactura))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnclientes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37)))
                        .addGap(12, 12, 12)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtruc1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtnomape)
                            .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtruc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel34)
                            .addComponent(txtcod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtdir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(btnproductosFactura))))
                .addContainerGap())
        );

        pnelVenta.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 800, 150));

        jPanel16.setBackground(new java.awt.Color(0, 153, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel43.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("SubTotal:");

        jLabel44.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("IVA:");

        jLabel45.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Total:");

        tbdetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        tbdet.setFont(new java.awt.Font("Agency FB", 0, 14)); // NOI18N
        tbdet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Precio", "Cantidad", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbdet);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(0, 18, Short.MAX_VALUE)
                    .addComponent(tbdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 18, Short.MAX_VALUE)))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGap(0, 108, Short.MAX_VALUE)
                    .addComponent(tbdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 108, Short.MAX_VALUE)))
        );

        pnelVenta.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, -1, 220));

        jPanel17.setBackground(new java.awt.Color(51, 153, 255));

        btnguardarFactura.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnguardarFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnguardarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pago.png"))); // NOI18N
        btnguardarFactura.setText("Realizar Venta");
        btnguardarFactura.setBorderPainted(false);
        btnguardarFactura.setContentAreaFilled(false);
        btnguardarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarFacturaActionPerformed(evt);
            }
        });

        btnsalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir_norm.png"))); // NOI18N
        btnsalir2.setBorderPainted(false);
        btnsalir2.setContentAreaFilled(false);
        btnsalir2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir_press.png"))); // NOI18N
        btnsalir2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir_roll.png"))); // NOI18N
        btnsalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir2ActionPerformed(evt);
            }
        });

        btncalcularFactura.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btncalcularFactura.setForeground(new java.awt.Color(255, 255, 255));
        btncalcularFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/calcular1.JPG"))); // NOI18N
        btncalcularFactura.setText("Realizar Calculo");
        btncalcularFactura.setBorderPainted(false);
        btncalcularFactura.setContentAreaFilled(false);
        btncalcularFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncalcularFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularFacturaActionPerformed(evt);
            }
        });

        btneliminarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_norm.png"))); // NOI18N
        btneliminarFactura.setBorderPainted(false);
        btneliminarFactura.setContentAreaFilled(false);
        btneliminarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneliminarFactura.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_press.png"))); // NOI18N
        btneliminarFactura.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar_roll.png"))); // NOI18N
        btneliminarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarFacturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnsalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnguardarFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncalcularFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneliminarFactura, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btncalcularFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnguardarFactura)
                .addGap(18, 18, 18)
                .addComponent(btneliminarFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnelVenta.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 370, 200, 230));
        pnelVenta.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        rSPanelsSlider1.add(pnelVenta, "card6");

        jPanel2.add(rSPanelsSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 760));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 1280, 760));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        // TODO add your handling code here:
         if(!inicio.btnUsers.isSelected()){
            inicio.btnClientes.setSelected(false);
            inicio.btnUsers.setSelected(true);
            inicio.btnProve.setSelected(false);
            inicio.btnProductos.setSelected(false);
            inicio.btnVenta.setSelected(false);
            inicio.btnRepor.setSelected(false);
            
            rSPanelsSlider1.setPanelSlider(pnelUsuario, RSPanelsSlider.DIRECT.RIGHT);
        }
        
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
         if(!inicio.btnClientes.isSelected()){
            inicio.btnClientes.setSelected(true);
            inicio.btnUsers.setSelected(false);
            inicio.btnProve.setSelected(false);
            inicio.btnProductos.setSelected(false);
            inicio.btnVenta.setSelected(false);
            inicio.btnRepor.setSelected(false);
            
            rSPanelsSlider1.setPanelSlider(pnelClientes, RSPanelsSlider.DIRECT.RIGHT);
        }
    }//GEN-LAST:event_btnClientesActionPerformed

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

    private void btnguardarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarUsuariosActionPerformed

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
            Usercontroller.create(dts);
            JOptionPane.showConfirmDialog(rootPane, "Usuario registrado satisfactoriamente");
            mostrarUsuarios("");
            limpiarUsuarios();
            inhabilitarUsuarios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el registro");
            System.out.println("error on save user = " + e.getMessage());
        }
    }//GEN-LAST:event_btnguardarUsuariosActionPerformed

    private void btncancelarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarUsuariosActionPerformed
        // TODO9 add your handling code here:
        limpiarUsuarios();
        inhabilitarUsuarios();
    }//GEN-LAST:event_btncancelarUsuariosActionPerformed

    private void btnnuevoUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoUsuariosActionPerformed
        // TODO add your handling code here:
        habilitar();
    }//GEN-LAST:event_btnnuevoUsuariosActionPerformed

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

    private void txtbuscarUsuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarUsuariosKeyReleased
        // TODO add your handling code here:
        mostrarUsuarios(txtbuscarUsuarios.getText());
    }//GEN-LAST:event_txtbuscarUsuariosKeyReleased

    private void btneliminarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarUsuariosActionPerformed
        // TODO add your handling code here:

        if (!txtidusuario.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de Eliminar el Usuario", "Confirmar", 2);

            try {
                if (confirmacion == 0) {

                    Usuario user;
                    user = Usercontroller.findUsuario(Integer.parseInt(txtidusuario.getText()));
                    Usercontroller.destroy(user.getId());
                    mostrarUsuarios("");
                    inhabilitarUsuarios();
                }
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btneliminarUsuariosActionPerformed

    private void btneditarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarUsuariosActionPerformed
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
            Usuario dts =Usercontroller.findUsuario(Integer.parseInt(txtidusuario.getText()));
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
            Usercontroller.edit(dts);
            JOptionPane.showConfirmDialog(rootPane, "Usuario actualizado satisfactoriamente");
            mostrarUsuarios("");
            limpiarUsuarios();
            inhabilitarUsuarios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el registro");
            System.out.println("error on save user = " + e.getMessage());
        }
    }//GEN-LAST:event_btneditarUsuariosActionPerformed

    private void btnbuscar1UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1UsuariosActionPerformed
        // TODO add your handling code here:
        mostrarUsuarios("");
    }//GEN-LAST:event_btnbuscar1UsuariosActionPerformed

    private void txtnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z') && (car!=(char)KeyEvent.VK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtnomKeyTyped

    private void txtapeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapeKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z') && (car!=(char)KeyEvent.VK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtapeKeyTyped

    private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdniActionPerformed

    private void txtdniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdniKeyPressed

    private void txtdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if(txtdni.getText().length()>=8) evt.consume();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtdniKeyTyped

    private void txttelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if(txttel.getText().length()>=9) evt.consume();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txttelKeyTyped

    private void txtdirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdirKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdirKeyTyped

    private void txtrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrucActionPerformed

    private void txtrucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        if(txtruc.getText().length()>=11) evt.consume();
        if((car<'0' || car>'9') && (car<'A' || car>'Z')) evt.consume();
        
    }//GEN-LAST:event_txtrucKeyTyped

    private void btnnuevoClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoClientesActionPerformed
        // TODO add your handling code here:
        desbloquearClientes();
        limpiarClientes();
        txtidcliente.requestFocus();
    }//GEN-LAST:event_btnnuevoClientesActionPerformed

    private void btnguardarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarClientesActionPerformed
        // TODO add your handling code here:
      
        if (txtnom.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar un nombre");
            txtnom.requestFocus();
            return;
        }

        if (txtape.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar los apellido");
            txtape.requestFocus();
            return;
        }

        if (txtdni.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el numero de cedula");
            txtdni.requestFocus();
            return;
        }

        if (txttel.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el celular");
            txttel.requestFocus();
            return;
        }
          try {
            Cliente cliente = new Cliente();

            cliente.setNombre(txtnom.getText());
            cliente.setCedula(Integer.parseInt(txtdni.getText()));
            cliente.setEmail(txtemail.getText());
            cliente.setRuc(txtruc.getText());
            cliente.setEstado("A");

            clienteController.create(cliente);
            JOptionPane.showMessageDialog(rootPane, "El cliente fue registrado satisfactoriamente");
            mostrarUsuarios("");
            limpiarUsuarios();
            bloquearClientes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el registro ");
              System.out.println("error = " + e.getMessage());
         }     
    }//GEN-LAST:event_btnguardarClientesActionPerformed

    private void btnactualizarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarClientesActionPerformed
        // TODO add your handling code here:
        if(txtnom.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar un nombre");
            txtnom.requestFocus();
            return;
        }

        if(txtape.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar los apellido");
            txtape.requestFocus();
            return;
        }

        if(txtdni.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el numero de cedula");
            txtdni.requestFocus();
            return;
        }

        if(txttel.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el celular");
            txttel.requestFocus();
            return;
        }

            try {
            Cliente cliente = clienteController.findCliente(Integer.parseInt(txtidcliente.getText()));

            cliente.setNombre(txtnom.getText());
            cliente.setCedula(Integer.parseInt(txtdni.getText()));
            cliente.setEmail(txtemail.getText());
            cliente.setRuc(txtruc.getText());
            cliente.setEstado("A");

            clienteController.edit(cliente);
            JOptionPane.showMessageDialog(rootPane, "El cliente fue registrado satisfactoriamente");
            mostrarUsuarios("");
            limpiarUsuarios();
            bloquearClientes();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el registro ");
              System.out.println("error = " + e.getMessage());
        }   
    }//GEN-LAST:event_btnactualizarClientesActionPerformed

    private void btncancelarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarClientesActionPerformed
        // TODO add your handling code here:
        limpiarClientes();
        bloquearClientes();
    }//GEN-LAST:event_btncancelarClientesActionPerformed

    private void tbclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesMouseClicked
        // TODO add your handling code here:
        //               desbloquear();
        btnactualizarClientes.setEnabled(true);
        int filamodificar= tbclientes.getSelectedRow();
        if(filamodificar>=0)
        {
            txtidcliente.setText(tbclientes.getValueAt(filamodificar, 0).toString());
            txtnom.setText(tbclientes.getValueAt(filamodificar, 1).toString());
            txtape.setText(tbclientes.getValueAt(filamodificar, 2).toString());
            txtruc.setText(tbclientes.getValueAt(filamodificar, 3).toString());
            txtdni.setText(tbclientes.getValueAt(filamodificar, 4).toString());
            txttel.setText(tbclientes.getValueAt(filamodificar, 5).toString());
            txtemail.setText(tbclientes.getValueAt(filamodificar, 6).toString());

        }
        else
        {
            JOptionPane.showMessageDialog(this,"No ha seleccionado ");
        }
    }//GEN-LAST:event_tbclientesMouseClicked

    private void txtbuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarClientesActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtbuscarClientesActionPerformed

    private void txtbuscarClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarClientesKeyReleased
        // TODO add your handling code here:
        mostrarClientes(txtbuscarUsuarios.getText());
    }//GEN-LAST:event_txtbuscarClientesKeyReleased

    private void btnbuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarClientesActionPerformed
        // TODO add your handling code here:
        mostrarClientes("");
    }//GEN-LAST:event_btnbuscarClientesActionPerformed

    private void tbmarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmarcaMouseClicked
        // TODO add your handling code here:
        desbloquearClientes();
        int fila =tbmarca.rowAtPoint(evt.getPoint());

        txtidmarca.setText(tbmarca.getValueAt(fila, 0).toString());
        txtnombremarca.setText(tbmarca.getValueAt(fila, 1).toString());
        txtdescripcionmarca.setText(tbmarca.getValueAt(fila, 2).toString());

    }//GEN-LAST:event_tbmarcaMouseClicked

    private void btnmostrarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarMarcaActionPerformed
        // TODO add your handling code here:
        mostrarMarcas("");
    }//GEN-LAST:event_btnmostrarMarcaActionPerformed

    private void txtbuscarMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarMarcaKeyReleased
        // TODO add your handling code here:
        mostrarMarcas(txtbuscarMarca.getText());
    }//GEN-LAST:event_txtbuscarMarcaKeyReleased

    private void btncancelarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarMarcaActionPerformed
        // TODO add your handling code here:
        bloquearMarca();

    }//GEN-LAST:event_btncancelarMarcaActionPerformed

    private void btnnuevoMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoMarcaActionPerformed
        // TODO add your handling code here:
        desbloquearMarca();
    }//GEN-LAST:event_btnnuevoMarcaActionPerformed

    private void btnguardarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarMarcaActionPerformed

        if(txtnombremarca.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar una marca");
            txtnombremarca.requestFocus();
            return;
        }

        if(txtdescripcionmarca.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar una descripcion");
            txtdescripcionmarca.requestFocus();
            return;
        }
        //
        //        vmarca dts =new vmarca();
        //        ftmarca func=new ftmarca();
        //
        //        dts.setmarca(txtnombremarca.getText());
        //        dts.setdescripcion(txtdescripcionmarca.getText());
        //
        //
        //            if(func.insertar(dts)){
            //                JOptionPane.showConfirmDialog(rootPane, "la marca fue registrada exitosamente");
            //                mostrar("");
            //                limpiar();
            //                bloquear();
            //            }
    }//GEN-LAST:event_btnguardarMarcaActionPerformed

    private void btneliminarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarMarcaActionPerformed
        // TODO add your handling code here:

        if(!txtidmarca.getText().equals("")){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "Esta seguro de Eliminar la marca","Confirmar",2);
            //
            //            if(confirmacion==0){
                //                ftmarca func=new ftmarca();
                //                vmarca dts=new vmarca();
                //
                //                dts.setidmarca(Integer.parseInt(txtidmarca.getText()));
                //                func.eliminar(dts);
                //                mostrar("");
                //                bloquear();
                //            }
            //
        }
    }//GEN-LAST:event_btneliminarMarcaActionPerformed

    private void btneditarMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarMarcaActionPerformed
        // TODO add your handling code here:
        if(txtnombremarca.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar una marca");
            txtnombremarca.requestFocus();
            return;
        }

        if(txtdescripcionmarca.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar una descripcion");
            txtdescripcionmarca.requestFocus();
            return;
        }

        //        vmarca dts =new vmarca();
        //        ftmarca func=new ftmarca();
        //
        //        dts.setmarca(txtnombremarca.getText());
        //        dts.setdescripcion(txtdescripcionmarca.getText());
        //        dts.setidmarca(Integer.parseInt(txtidmarca.getText()));
        //
        //
        //            if(func.editar(dts)){
            //                JOptionPane.showConfirmDialog(rootPane, "la marca fue registrada exitosamente");
            //                mostrar("");
            //                limpiar();
            //                bloquear();
            //            }
    }//GEN-LAST:event_btneditarMarcaActionPerformed

    private void txtmodeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmodeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmodeloActionPerformed

    private void cbomarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomarcaActionPerformed

    private void tbproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductoMouseClicked
        // TODO add your handling code here:
        int fila= tbproducto.getSelectedRow();
        try{
            System.out.println("fila = " + fila);
            if(fila>=0)
            {
                Idproducto.setText(tbproducto.getValueAt(fila, 0).toString());
                txtproducto.setText(tbproducto.getValueAt(fila, 1).toString());
                txtmodelo.setText(tbproducto.getValueAt(fila, 2).toString());
                txtcantidad.setText(tbproducto.getValueAt(fila, 3).toString());
                txtprecio.setText(tbproducto.getValueAt(fila, 4).toString());

            }
            else
            {
                JOptionPane.showMessageDialog(this,"No ha seleccionado ");
            }
        }catch(Exception e){
            System.out.println("e = " + e.getMessage());
        }
    }//GEN-LAST:event_tbproductoMouseClicked

    private void tbproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbproductoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbproductoKeyPressed

    private void txtbuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarProductoActionPerformed

    private void txtbuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarProductoKeyReleased
        // TODO add your handling code here:
        mostrarProductos(txtbuscarProducto.getText());
    }//GEN-LAST:event_txtbuscarProductoKeyReleased

    private void btnbuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarProductoActionPerformed
        // TODO add your handling code here:
        mostrarProductos("");
    }//GEN-LAST:event_btnbuscarProductoActionPerformed

    private void btnnuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoProductoActionPerformed
        // TODO add your handling code here:
        desbloquearProducto();
    }//GEN-LAST:event_btnnuevoProductoActionPerformed

    private void btnguardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarProductoActionPerformed
        // TODO add your handling code here:
        if(txtproducto.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el producto");
            txtproducto.requestFocus();
            return;
        }

        if(txtmodelo.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el modelo");
            txtmodelo.requestFocus();
            return;
        }

        if(txtcantidad.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar la cantidad");
            txtcantidad.requestFocus();
            return;
        }

        if(txtprecio.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el precio");
            txtprecio.requestFocus();
            return;
        }
        //
        ////        vproducto dts =new vproducto();
        ////        ftproductos func=new ftproductos();
        //
        //        dts.setNombre(txtproducto.getText());
        //        String modelo = txtmodelo.getText();
        //        String cantidad = txtcantidad.getText();
        //        String precio = txtprecio.getText();
        //        int seleccionado=cbomarca.getSelectedIndex();
        ////        vmarca marca = (vmarca) cbomarca.getSelectedItem();
        ////        dts.setidmarca(marca.getidmarca());
        //        dts.setcantidad(cantidad);
        //        dts.setprecio(precio);
        //        dts.setmodelo(modelo);
        //
        //
        //
        //
        //            if(func.insertar(dts)){
            //                JOptionPane.showConfirmDialog(rootPane, "El producto fue registrado satisfactoriamente");
            //                mostrar("");
            //                limpiar();
            //                bloquear();
            //            }

    }//GEN-LAST:event_btnguardarProductoActionPerformed

    private void btneditarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarProductoActionPerformed
        // TODO add your handling code here:

        if(txtproducto.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el producto");
            txtproducto.requestFocus();
            return;
        }

        if(txtmodelo.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el modelo");
            txtmodelo.requestFocus();
            return;
        }

        if(txtcantidad.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar la cantidad");
            txtcantidad.requestFocus();
            return;
        }

        if(txtprecio.getText().length()==0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Ingresar el precio");
            txtprecio.requestFocus();
            return;
        }

        //        vproducto dts =new vproducto();
        //        ftproductos func=new ftproductos();
        //
        //        dts.setNombre(txtproducto.getText());
        //        String modelo = txtmodelo.getText();
        //        String cantidad = txtcantidad.getText();
        //        String precio = txtprecio.getText();
        //        int seleccionado=cbomarca.getSelectedIndex();
        ////        vmarca marca = (vmarca) cbomarca.getSelectedItem();
        //        dts.setidmarca(marca.getidmarca());
        //        dts.setcantidad(cantidad);
        //        dts.setprecio(precio);
        //        dts.setmodelo(modelo);
        //        int fila = tbproducto.getSelectedRow();
        //        int idproducto=Integer.parseInt(tbproducto.getValueAt(fila, 0).toString());
        //        dts.setidproducto(idproducto);
        //
        //            if(func.editar(dts)){
            //                JOptionPane.showConfirmDialog(rootPane, "El producto fue registrado satisfactoriamente");
            //                mostrar("");
            //                limpiar();
            //                bloquear();
            //            }
    }//GEN-LAST:event_btneditarProductoActionPerformed

    private void btneliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarProductoActionPerformed
        // TODO add your handling code here:
        if(!Idproducto.getText().equals("")){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "Esta seguro de Eliminar el producto","Confirmar",2);

            if(confirmacion==0){
                //                ftproductos func=new ftproductos();
                //                vproducto dts=new vproducto();

                //             dts.setidproducto(Integer.parseInt(Idproducto.getText()));
                //                func.eliminar(dts);
                //                mostrar("");
                //                //inhabilitar();
            }
        }
    }//GEN-LAST:event_btneliminarProductoActionPerformed

    private void btncancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarProductoActionPerformed
        // TODO add your handling code here:
        bloquearProducto();
    }//GEN-LAST:event_btncancelarProductoActionPerformed

    private void txtnomapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomapeActionPerformed

    private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
        // TODO add your handling code here:
        tabla_clientes cli = new tabla_clientes();
        frminicio.Escritorio.add(cli);
        cli.toFront();
        cli.setVisible(true);

    }//GEN-LAST:event_btnclientesActionPerformed

    private void btnproductosFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosFacturaActionPerformed
        // TODO add your handling code here:
        try {
            tabla_productos pro= new tabla_productos();
            frminicio.Escritorio.add(pro);
            pro.toFront();
            pro.setVisible(true);

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnproductosFacturaActionPerformed

    private void btnguardarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarFacturaActionPerformed
        // TODO add your handling code here:
        if((txtidcliente.getText().equals("")) || (txtsubtotal.getText().equals("")))
        {
            JOptionPane.showMessageDialog(this, "No ingreso cliente,productos o realice operacion");
        }
        else
        {
            String capcod="",capcan="";
            for(int i=0;i<frmfactura.tbdet.getRowCount();i++)
            {
                capcod=frmfactura.tbdet.getValueAt(i, 0).toString();
                capcan=frmfactura.tbdet.getValueAt(i, 3).toString();
//                descontarstock(capcod, capcan);

            }
//            factura();
            //    detallefactura();
//            mostrarFactura();

            txtidcliente.setText("");
            txtnomape.setText("");
            txtdir.setText("");
            txtdni.setText("");
            txtigv.setText("");
            txtsubtotal.setText("");
            txtruc.setText("");
            txttotal.setText("");

            DefaultTableModel modelo = (DefaultTableModel) tbdet.getModel();
            int a =tbdet.getRowCount()-1;
            int i;
            for(i=a;i>=0;i--)
            {
                modelo.removeRow(i);
            }

//            numeros();
        }

    }//GEN-LAST:event_btnguardarFacturaActionPerformed

    private void btnsalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalir2ActionPerformed

    private void btncalcularFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularFacturaActionPerformed
        // TODO add your handling code here:
        if(tbdet.getRowCount()<1)
        {
            JOptionPane.showMessageDialog(this, "Error, no ingreso ningun producto");
        }
        else{
//            calcular();
        }                                    


    }//GEN-LAST:event_btncalcularFacturaActionPerformed

    private void btneliminarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarFacturaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tbdet.getModel();
        int fila = tbdet.getSelectedRow();
        if(fila>=0)
        {
            modelo.removeRow(fila);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No Selecciono ninguna fila");
        }
    }//GEN-LAST:event_btneliminarFacturaActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        // TODO add your handling code here:
        if(!inicio.btnProductos.isSelected()){
            inicio.btnClientes.setSelected(false);
            inicio.btnUsers.setSelected(false);
            inicio.btnProve.setSelected(false);
            inicio.btnProductos.setSelected(true);
            inicio.btnVenta.setSelected(false);
            inicio.btnRepor.setSelected(false);
            
            rSPanelsSlider1.setPanelSlider(pnelProducto, RSPanelsSlider.DIRECT.RIGHT);
        }
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnProveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveActionPerformed
        // TODO add your handling code here:
        if(!inicio.btnProve.isSelected()){
            inicio.btnClientes.setSelected(false);
            inicio.btnUsers.setSelected(false);
            inicio.btnProve.setSelected(true);
            inicio.btnProductos.setSelected(false);
            inicio.btnVenta.setSelected(false);
            inicio.btnRepor.setSelected(false);
            
            rSPanelsSlider1.setPanelSlider(pnelClientes, RSPanelsSlider.DIRECT.RIGHT);
        }
    }//GEN-LAST:event_btnProveActionPerformed

    private void btnReporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporActionPerformed
        // TODO add your handling code here:
        if(!inicio.btnRepor.isSelected()){
            inicio.btnClientes.setSelected(false);
            inicio.btnUsers.setSelected(false);
            inicio.btnProve.setSelected(false);
            inicio.btnProductos.setSelected(false);
            inicio.btnVenta.setSelected(false);
            inicio.btnRepor.setSelected(true);
            
            rSPanelsSlider1.setPanelSlider(pnelClientes, RSPanelsSlider.DIRECT.RIGHT);
        }
    }//GEN-LAST:event_btnReporActionPerformed

    private void btnVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaActionPerformed
        // TODO add your handling code here:
        if(!inicio.btnVenta.isSelected()){
            inicio.btnClientes.setSelected(false);
            inicio.btnUsers.setSelected(false);
            inicio.btnProve.setSelected(false);
            inicio.btnProductos.setSelected(false);
            inicio.btnVenta.setSelected(true);
            inicio.btnRepor.setSelected(false);

            rSPanelsSlider1.setPanelSlider(pnelVenta, RSPanelsSlider.DIRECT.RIGHT);
        }
    }//GEN-LAST:event_btnVentaActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Idproducto;
    public static rojeru_san.RSButton btnClientes;
    public static rojeru_san.RSButton btnProductos;
    public static rojeru_san.RSButton btnProve;
    public static rojeru_san.RSButton btnRepor;
    public static rojeru_san.RSButton btnUsers;
    public static rojeru_san.RSButton btnVenta;
    private javax.swing.JButton btnactualizarClientes;
    private javax.swing.JButton btnbuscar1Usuarios;
    private javax.swing.JButton btnbuscarClientes;
    private javax.swing.JButton btnbuscarProducto;
    private javax.swing.JButton btncalcularFactura;
    private javax.swing.JButton btncancelarClientes;
    private javax.swing.JButton btncancelarMarca;
    private javax.swing.JButton btncancelarProducto;
    private javax.swing.JButton btncancelarUsuarios;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneditarMarca;
    private javax.swing.JButton btneditarProducto;
    private javax.swing.JButton btneditarUsuarios;
    private javax.swing.JButton btneliminarFactura;
    private javax.swing.JButton btneliminarMarca;
    private javax.swing.JButton btneliminarProducto;
    private javax.swing.JButton btneliminarUsuarios;
    private javax.swing.JButton btnguardarClientes;
    private javax.swing.JButton btnguardarFactura;
    private javax.swing.JButton btnguardarMarca;
    private javax.swing.JButton btnguardarProducto;
    private javax.swing.JButton btnguardarUsuarios;
    private javax.swing.JButton btnmostrarMarca;
    private javax.swing.JButton btnnuevoClientes;
    private javax.swing.JButton btnnuevoMarca;
    private javax.swing.JButton btnnuevoProducto;
    private javax.swing.JButton btnnuevoUsuarios;
    private javax.swing.JButton btnproductosFactura;
    private javax.swing.JButton btnsalir2;
    private javax.swing.JComboBox cboacceso1;
    private javax.swing.JComboBox cboestado;
    private javax.swing.JComboBox<String> cbomarca;
    private com.toedter.calendar.JDateChooser dcfecha_ingreso;
    private org.jdesktop.swing.animation.timing.evaluators.EvaluatorLine2D evaluatorLine2D1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JLabel lblacceso;
    private javax.swing.JLabel lblhora;
    public static javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lbltotalregistros;
    private javax.swing.JPanel pnelClientes;
    private javax.swing.JPanel pnelMarca;
    private javax.swing.JPanel pnelProducto;
    private javax.swing.JPanel pnelUsuario;
    private javax.swing.JPanel pnelVenta;
    private rojeru_san.RSPanelsSlider rSPanelsSlider1;
    private javax.swing.JPanel sidebar;
    private javax.swing.JTable tablausuario;
    private javax.swing.JTable tbclientes;
    public static javax.swing.JTable tbdet;
    private javax.swing.JTable tbdetalle;
    private javax.swing.JTable tbmarca;
    private javax.swing.JTable tbproducto;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtbuscarClientes;
    private javax.swing.JTextField txtbuscarMarca;
    private javax.swing.JTextField txtbuscarProducto;
    private javax.swing.JTextField txtbuscarUsuarios;
    private javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtcod1;
    private javax.swing.JTextField txtdescripcionmarca;
    private javax.swing.JTextField txtdir;
    public static javax.swing.JTextField txtdir1;
    private javax.swing.JTextField txtdni;
    public static javax.swing.JTextField txtdni1;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtfac;
    private javax.swing.JTextField txtfec;
    private javax.swing.JTextField txtidcliente;
    private javax.swing.JTextField txtidmarca;
    private javax.swing.JTextField txtidusuario;
    private javax.swing.JTextField txtigv;
    private javax.swing.JTextField txtlogin;
    private javax.swing.JTextField txtmodelo;
    private javax.swing.JTextField txtnom;
    public static javax.swing.JTextField txtnomape;
    private javax.swing.JTextField txtnombremarca;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtproducto;
    private javax.swing.JTextField txtruc;
    public static javax.swing.JTextField txtruc1;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttel;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables

    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblhora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

    }

}
