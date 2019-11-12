
/*
 * Factura.java
 *
 * @author elaprendiz http://www.youtube.com/user/JleoD7
 */
package vistas;

//import Modelo.lista_factura;
//import Servicios.GenerarNumero;
//import Servicios.conexion;
//import Servicios.ftfactura;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class frmfactura extends javax.swing.JInternalFrame {
     public Map parametros = new HashMap();
    /** Creates new form Factura */
    public frmfactura() {
        initComponents();

        this.setLocation(25,15 );
        txtfac.setEnabled(false);
        txtfec.setEnabled(false);
        txtfec.setDisabledTextColor(Color.blue);
        txtfec.setText(fechaactual());
        numeros();
       
      
        
    }
          void descontarstock(String codi,String can)
    {
       int des = Integer.parseInt(can);
       String cap="";
       int desfinal;
       String consul="SELECT cantidad FROM producto WHERE  idproducto='"+codi+"'";
//        try {
//            Statement st= cn.createStatement();
//            ResultSet rs= st.executeQuery(consul);
//            while(rs.next())
//            {
//                cap= rs.getString(1);
//            }
//            
//            
//        } catch (Exception e) {
//        }
        desfinal=Integer.parseInt(cap)-des;
        String modi="UPDATE producto SET cantidad='"+desfinal+"' WHERE idproducto = '"+codi+"'";
//        try {
//            PreparedStatement pst = cn.prepareStatement(modi);
//            pst.executeUpdate();
//        } catch (Exception e) {
//        }
        
       
         
    }
     void numeros()
     {
        int j;
        int cont=1;
        String num="";
        String c="";
         String SQL="select max(numero_fac) from factura";
       // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs=st.executeQuery(SQL);
//            if(rs.next())
//            {              
//                 c=rs.getString(1);
//            }
//            
//           
//            if(c==null){
//                txtfac.setText("00000001");
//            }
//            else{
//                 j=Integer.parseInt(c);
//                 GenerarNumero gen= new GenerarNumero();
//                 gen.generar(j);
//                 txtfac.setText(gen.serie());
//                
//            
//            }
// 
//         
//        } catch (SQLException ex) {
//           Logger.getLogger(frmfactura.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    void calcular()
    {
        String pre;
        String can;
        double igv=0;
        double total=0;
        double subtotal=0;
        double precio;
        int cantidad;
        double imp=0.0;
        
            /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/
        
        for(int i=0;i<tbdet.getRowCount();i++)
        {
            pre=tbdet.getValueAt(i, 2).toString();
            can=tbdet.getValueAt(i, 3).toString();
            precio=Double.parseDouble(pre);
            cantidad=Integer.parseInt(can);
            imp=precio*cantidad;
            subtotal=subtotal+imp;
            igv=subtotal*0.18;
            total=subtotal+igv;
            // txtcod.setText(""+Math.rint(c*100)/100)
            tbdet.setValueAt(Math.rint(imp*100)/100, i, 4);
            
        }
        txtsubtotal.setText(Double.toString(subtotal));
        txtigv.setText(""+Math.rint(igv*100)/100);
        txttotal.setText(""+Math.rint(total*100)/100);
        
            
    }

    
    void factura(){
//       String InsertarSQL="INSERT INTO factura (numero_fac,cliente_idcliente,usuario_idusuario,fecha,estado,subtotal,iva,totalfactura) VALUES (?,?,?,CURRENT_DATE,?,?,?,?)";
//    String numfac=txtfac.getText();
//    String codcli=txtcod.getText();
//    String subtotal=txtsubtotal.getText();
//    String igv=txtigv.getText();
//    String total=txttotal.getText();
//    
//        System.out.println("igv = " + igv);
//        System.out.println("total = " + total);
//    
//    try {
//            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
//            pst.setString(1,numfac);
//            pst.setString(2,codcli);
//            pst.setInt(3, 1);
//            pst.setString(4,"Pendiente");
//            pst.setString(5,subtotal);
//            pst.setString(6,igv);
//            pst.setString(7,total);
//            int n= pst.executeUpdate();
//            if(n>0)
//            {
//                JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");
//            }
//            System.out.println("n = " + n);
//           
//        } catch (SQLException ex) {
//            Logger.getLogger(frmfactura.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("ex = " + ex.getMessage());
//        }
    }
//    void detallefactura(){
//        ftfactura func = new ftfactura();
//        String id = func.getLastFactura();
//        if (id != null) {
//            System.out.println("count:" + tbdet.getRowCount());
//            for (int i = 0; i < tbdet.getRowCount(); i++) {
//                String InsertarSQL = "INSERT INTO detalle (factura_idfactura,producto_idproducto,cantidad,descuento,totalventa) VALUES (?,?,?,?,?)";
//                String codpro = tbdet.getValueAt(i, 0).toString();
//                String despro = tbdet.getValueAt(i, 1).toString();
//                String importe = tbdet.getValueAt(i, 4).toString();
//                String cantidad = tbdet.getValueAt(i,3).toString();
//                System.out.println("codpro = " + codpro);
//                System.out.println("despro = " + despro);
//                System.out.println("importe = " + importe);
//
//        try {
//            PreparedStatement pst = cn.prepareStatement(InsertarSQL);
//                    pst.setInt(1, Integer.parseInt(id));
//                    pst.setString(2, codpro);
//                    pst.setString(3,cantidad);
//                    pst.setString(4, "0");
//                    pst.setString(5, importe);
//            pst.executeUpdate();
//          
//        } catch (SQLException ex) {
//            Logger.getLogger(frmfactura.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            }
//        }else{
//             JOptionPane.showMessageDialog(null,"Error al recuperar el id de la factura para el detalle");
//        }
//    }
    
    void mostrarFactura(){
         JasperReport reporte = null;
    List lista = new ArrayList();
    for (int i = 0; i < tbdet.getRowCount(); i++) {

        JTable tabla = tbdet;

//        lista_factura factura = new lista_factura(
//                txtnomape.getText(),
//                txtfec.getText(),
//                txtruc.getText(),
//                txtfac.getText(),
//                tabla.getValueAt(i, 1).toString(),
//                Integer.parseInt(tabla.getValueAt(i, 3).toString()),
//                Integer.parseInt(tabla.getValueAt(i, 2).toString()),
//                txtsubtotal.getText(),
//                txttotal.getText()
//        );
//        System.out.println("tabla = " + tabla.getValueAt(i, 1).toString());
//        System.out.println("tabla = " + tabla.getValueAt(i, 3).toString());
//        System.out.println("tabla = " + tabla.getValueAt(i, 2).toString());
//        lista.add(factura);

        try {
            String path = "src\\reportes\\factura.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            Map parametro = new HashMap();
            JasperPrint jprPrint = JasperFillManager.fillReport(path, parametro, new JRBeanCollectionDataSource(lista));
            JasperViewer view = new JasperViewer(jprPrint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(frmfactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
//    List<Map<String, Object>> dataSource = new  ArrayList<>();
//    
//        JTable tabla = tbdet;
//                for (int i = 0; i < tbdet.getRowCount(); i++) {
//        Map<String, Object>  factura = new HashMap<>();
//                factura.put("cliente", txtnomape.getText());
//                factura.put("fecha",txtfec.getText());
//                factura.put("ruc",txtruc.getText());
//                factura.put("factura",txtfac.getText());
//                factura.put("producto",tabla.getValueAt(i, 1).toString());
//                int cantidad = Integer.parseInt(tabla.getValueAt(i, 3).toString());
//                int precio = Integer.parseInt(tabla.getValueAt(i, 2).toString());
//                factura.put("precio",precio);
//                factura.put("cantidad",cantidad);
//                factura.put("subtotal",txtsubtotal.getText());
//                factura.put("total",txttotal.getText());
//
//        dataSource.add(factura);
//        
//        try {
//            JRDataSource jRDataSource = new JRBeanCollectionDataSource(dataSource);
//        JasperReport jasperReport = JasperCompileManager.compileReport("src\\reportes\\factura.jrxml");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,jRDataSource);
//        JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
//        jasperViewer.setVisible(true);
////        System.out.println("jRDataSource = " + jRDataSource);
//        } catch (JRException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//            Logger.getLogger(frmfactura.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }
    }
    
    
 
  
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        btnclientes = new javax.swing.JButton();
        txtruc = new javax.swing.JTextField();
        btnproductos = new javax.swing.JButton();
        txtcod = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        txtdir = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtfac = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtigv = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        tbdetalle = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdet = new javax.swing.JTable();
        jSeparator9 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btncalcular = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FACTURA");
        setPreferredSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(null);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(12, 318, 0, 0);

        jPanel1.setBackground(new java.awt.Color(33, 45, 62));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 181, 172), 2));
        jPanel1.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText(" BOUTIQUE TIA ELVA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, 34));

        jLabel4.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Av. Luis Alberto del parana. c/ bejarano");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 55, -1, -1));

        jLabel5.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Telf: 0981675321");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 55, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono de formulario usuario/icons8-camiseta-forester-64.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 3, 83, 122));

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Asuncion - Paraguay");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(90, 10, 670, 120);

        jPanel2.setBackground(new java.awt.Color(33, 45, 62));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(73, 181, 172), 2, true));
        jPanel2.setForeground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Señor(a):");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(10, 10, 60, 40);

        txtnomape.setBackground(new java.awt.Color(33, 45, 62));
        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setForeground(new java.awt.Color(255, 255, 255));
        txtnomape.setText("FULANO");
        txtnomape.setBorder(null);
        txtnomape.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtnomape.setEnabled(false);
        txtnomape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomapeActionPerformed(evt);
            }
        });
        jPanel2.add(txtnomape);
        txtnomape.setBounds(90, 20, 195, 20);

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-CLIENTE.png"))); // NOI18N
        btnclientes.setToolTipText("");
        btnclientes.setBorder(null);
        btnclientes.setBorderPainted(false);
        btnclientes.setContentAreaFilled(false);
        btnclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnclientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/CLIENTE.png"))); // NOI18N
        btnclientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/CLIENTE.png"))); // NOI18N
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnclientes);
        btnclientes.setBounds(290, 10, 90, 40);

        txtruc.setBackground(new java.awt.Color(33, 45, 62));
        txtruc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtruc.setForeground(new java.awt.Color(255, 255, 255));
        txtruc.setText("5121528-4");
        txtruc.setBorder(null);
        txtruc.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtruc.setEnabled(false);
        jPanel2.add(txtruc);
        txtruc.setBounds(430, 20, 120, 20);

        btnproductos.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        btnproductos.setForeground(new java.awt.Color(255, 255, 255));
        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-BUSCAR.png"))); // NOI18N
        btnproductos.setBorder(null);
        btnproductos.setBorderPainted(false);
        btnproductos.setContentAreaFilled(false);
        btnproductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnproductos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-BUSCAR.png"))); // NOI18N
        btnproductos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-BUSCAR.png"))); // NOI18N
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });
        jPanel2.add(btnproductos);
        btnproductos.setBounds(440, 140, 85, 43);

        txtcod.setBackground(new java.awt.Color(33, 45, 62));
        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.setForeground(new java.awt.Color(255, 255, 255));
        txtcod.setText("bue01316");
        txtcod.setBorder(null);
        txtcod.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtcod.setEnabled(false);
        jPanel2.add(txtcod);
        txtcod.setBounds(90, 160, 200, 20);

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cod. Cliente:");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 160, 80, 24);

        txtdni.setBackground(new java.awt.Color(33, 45, 62));
        txtdni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdni.setForeground(new java.awt.Color(255, 255, 255));
        txtdni.setText("5121528");
        txtdni.setBorder(null);
        txtdni.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtdni.setEnabled(false);
        jPanel2.add(txtdni);
        txtdni.setBounds(90, 80, 195, 14);

        txtdir.setBackground(new java.awt.Color(33, 45, 62));
        txtdir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdir.setForeground(new java.awt.Color(255, 255, 255));
        txtdir.setText("SAN LORENZO");
        txtdir.setBorder(null);
        txtdir.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtdir.setEnabled(false);
        txtdir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdirActionPerformed(evt);
            }
        });
        jPanel2.add(txtdir);
        txtdir.setBounds(90, 120, 200, 20);

        jLabel10.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Direccion:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(10, 120, 70, 24);

        jLabel9.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DNI:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(10, 70, 40, 30);

        jLabel13.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("RUC:");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(390, 20, 40, 20);

        jLabel12.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Fecha:");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(390, 70, 40, 24);

        txtfec.setBackground(new java.awt.Color(33, 45, 62));
        txtfec.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtfec.setForeground(new java.awt.Color(255, 255, 255));
        txtfec.setText("2019-02-11");
        txtfec.setBorder(null);
        txtfec.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtfec);
        txtfec.setBounds(430, 70, 102, 20);

        jLabel14.setFont(new java.awt.Font("Agency FB", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Articulo:");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(390, 150, 60, 24);

        jPanel3.setBackground(new java.awt.Color(33, 45, 62));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 181, 172)));

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("FACTURA DE VENTA");

        jLabel6.setFont(new java.awt.Font("Agency FB", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("RUC 10046495581");

        jLabel17.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nº");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtfac))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15))))
                .addContainerGap(36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(11, 11, 11)
                .addComponent(jLabel15)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtfac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(602, 42, 156, 115);

        jSeparator2.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator2.setForeground(new java.awt.Color(73, 181, 172));
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(90, 140, 200, 9);

        jSeparator4.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator4.setForeground(new java.awt.Color(73, 181, 172));
        jPanel2.add(jSeparator4);
        jSeparator4.setBounds(90, 180, 200, 9);

        jSeparator5.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator5.setForeground(new java.awt.Color(73, 181, 172));
        jPanel2.add(jSeparator5);
        jSeparator5.setBounds(90, 100, 200, 9);

        jSeparator6.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator6.setForeground(new java.awt.Color(73, 181, 172));
        jPanel2.add(jSeparator6);
        jSeparator6.setBounds(90, 40, 200, 9);

        jSeparator7.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator7.setForeground(new java.awt.Color(73, 181, 172));
        jPanel2.add(jSeparator7);
        jSeparator7.setBounds(430, 40, 130, 9);

        jSeparator8.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator8.setForeground(new java.awt.Color(73, 181, 172));
        jPanel2.add(jSeparator8);
        jSeparator8.setBounds(430, 90, 130, 9);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(40, 140, 770, 200);

        jPanel4.setBackground(new java.awt.Color(33, 45, 62));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(73, 181, 172), 2, true));
        jPanel4.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("SubTotal:");
        jPanel4.add(jLabel18);
        jLabel18.setBounds(10, 140, 60, 30);

        txtsubtotal.setBackground(new java.awt.Color(33, 45, 62));
        txtsubtotal.setForeground(new java.awt.Color(255, 255, 255));
        txtsubtotal.setText("100.000 gs");
        txtsubtotal.setBorder(null);
        jPanel4.add(txtsubtotal);
        txtsubtotal.setBounds(70, 140, 120, 30);

        jLabel19.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("IVA:");
        jPanel4.add(jLabel19);
        jLabel19.setBounds(206, 140, 30, 30);

        txtigv.setBackground(new java.awt.Color(33, 45, 62));
        txtigv.setForeground(new java.awt.Color(255, 255, 255));
        txtigv.setText("10.000 gs");
        txtigv.setBorder(null);
        txtigv.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtigv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtigvActionPerformed(evt);
            }
        });
        jPanel4.add(txtigv);
        txtigv.setBounds(240, 140, 68, 30);

        jLabel20.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Total:");
        jPanel4.add(jLabel20);
        jLabel20.setBounds(409, 140, 40, 30);

        txttotal.setBackground(new java.awt.Color(33, 45, 62));
        txttotal.setForeground(new java.awt.Color(255, 255, 255));
        txttotal.setText("110.000 gs");
        txttotal.setBorder(null);
        jPanel4.add(txttotal);
        txttotal.setBounds(448, 140, 110, 30);

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
        jPanel4.add(tbdetalle);
        tbdetalle.setBounds(65, 110, 450, 0);

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
        jScrollPane2.setViewportView(tbdet);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(12, 13, 546, 116);

        jSeparator9.setBackground(new java.awt.Color(73, 181, 172));
        jSeparator9.setForeground(new java.awt.Color(73, 181, 172));
        jPanel4.add(jSeparator9);
        jSeparator9.setBounds(70, 170, 490, 20);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(40, 350, 580, 220);

        jPanel5.setBackground(new java.awt.Color(33, 45, 62));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(73, 181, 172), 2, true));

        btnguardar.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-CONFIRMAR.png"))); // NOI18N
        btnguardar.setBorder(null);
        btnguardar.setBorderPainted(false);
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/CONFIRMAR.png"))); // NOI18N
        btnguardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/CONFIRMAR.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-SALIR.png"))); // NOI18N
        btnsalir.setBorder(null);
        btnsalir.setBorderPainted(false);
        btnsalir.setContentAreaFilled(false);
        btnsalir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/SALIR.png"))); // NOI18N
        btnsalir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/SALIR.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btncalcular.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        btncalcular.setForeground(new java.awt.Color(255, 255, 255));
        btncalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-CALCULAR.png"))); // NOI18N
        btncalcular.setBorder(null);
        btncalcular.setBorderPainted(false);
        btncalcular.setContentAreaFilled(false);
        btncalcular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncalcular.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/CALCULAR.png"))); // NOI18N
        btncalcular.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/CALCULAR.png"))); // NOI18N
        btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularActionPerformed(evt);
            }
        });

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/BTN-DELETE.png"))); // NOI18N
        btneliminar.setBorder(null);
        btneliminar.setBorderPainted(false);
        btneliminar.setContentAreaFilled(false);
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/ELIMINAR.png"))); // NOI18N
        btneliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/new botons/PRESS ICONS/ELIMINAR.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btncalcular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsalir)
                .addContainerGap())
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(650, 350, 166, 220);

        jLabel1.setBackground(new java.awt.Color(33, 45, 62));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondo_inicio/NEWDFONDO.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 860, 620);

        setBounds(0, 0, 859, 649);
    }// </editor-fold>//GEN-END:initComponents
public static String fechaactual(){
    Date fecha= new Date();
    SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
    return formatofecha.format(fecha);
    


}
private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
// TODO add your handling code here:
    tabla_clientes cli = new tabla_clientes();
    frminicio.Escritorio.add(cli);
    cli.toFront();
    cli.setVisible(true);
    
  
    
}//GEN-LAST:event_btnclientesActionPerformed

private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
// TODO add your handling code here:
    try {
          tabla_productos pro= new tabla_productos();
    frminicio.Escritorio.add(pro);
    pro.toFront();
    pro.setVisible(true);
        
    } catch (Exception e) {
    }
     
}//GEN-LAST:event_btnproductosActionPerformed

private void txtnomapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomapeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtnomapeActionPerformed

private void btncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularActionPerformed
// TODO add your handling code here:
    if(tbdet.getRowCount()<1)
    {
        JOptionPane.showMessageDialog(this, "Error, no ingreso ningun producto");
    }
    else{
    calcular();
    }
    
}//GEN-LAST:event_btncalcularActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
// TODO add your handling code here:
  if((txtcod.getText().equals("")) || (txtsubtotal.getText().equals("")))
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
        descontarstock(capcod, capcan);
        
    }
    factura();
//    detallefactura();
    mostrarFactura();
    
        txtcod.setText("");
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
      
    numeros();
  }
      
    

}//GEN-LAST:event_btnguardarActionPerformed

private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
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
}//GEN-LAST:event_btneliminarActionPerformed

    private void txtdirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdirActionPerformed

    private void txtigvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtigvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtigvActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncalcular;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable tbdet;
    private javax.swing.JTable tbdetalle;
    public static javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtdir;
    public static javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfac;
    private javax.swing.JTextField txtfec;
    private javax.swing.JTextField txtigv;
    public static javax.swing.JTextField txtnomape;
    public static javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables


//    private conexion mysql=new conexion();
//    private Connection cn=mysql.conectar();

    private void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
