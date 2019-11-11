/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controladores.FacturaJpaController;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Cliente;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Usuario;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vistas.frmfactura;

/**
 *
 * @author matias
 */
public class FacturaDao {

    private final FacturaJpaController controller;
    private final Factura factura;
    private String mensaje = "";

    public FacturaDao() {
        this.controller = new FacturaJpaController();
        this.factura = new Factura();
    }

    public Factura create(Cliente cliente, String estado, Date fecha, Usuario user, String iva, String numeroFac, String subtotal, String total, Collection<DetalleFactura> detalleFactura) {
        try {
            factura.setClienteId(cliente);
            factura.setEstado(estado);
            factura.setFecha(fecha);
            factura.setUsuarioId(user);
            System.out.println("iva= " + iva);
            factura.setIva(iva);
            factura.setNumeroFac(numeroFac);
            factura.setSubtotal(subtotal);
            factura.setTotalfactura(total);
            factura.setDetalleFacturaCollection(detalleFactura);
            Factura Factura = controller.create(factura);
            return Factura;
        } catch (Exception e) {
            System.out.println("FacturaDao::create error on save = " + e.getMessage());
            return null;
        }
    }
    
    
    public String update(int id, Cliente cliente, String estado, Date fecha, Usuario user, String iva, String numeroFac, String subtotal, String total, Collection<DetalleFactura> detalleFactura) {
        try {
            factura.setId(id);
            factura.setClienteId(cliente);
            factura.setEstado(estado);
            factura.setFecha(fecha);
            factura.setUsuarioId(user);
            factura.setIva(iva);
            factura.setNumeroFac(numeroFac);
            factura.setSubtotal(subtotal);
            factura.setTotalfactura(total);
            factura.setDetalleFacturaCollection(detalleFactura);
            controller.edit(factura);
            mensaje = "Factura actualizada correctamente";
        } catch (Exception e) {
            mensaje  = "No se pudo guardar la Factura ";
            System.out.println("FacturaDao::update error on save = " + e.getMessage());
        }
        return mensaje;
    }
    
    
    public void printInvoice(int id){
         try {
//             List lista = new ArrayList();
             JRDataSource datasource = new JREmptyDataSource();
            Factura invoice = controller.findFactura(id);
            JasperReport reporte = JasperCompileManager.compileReport("src\\main\\java\\reportes\\factura.jasper");
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("", id);
            invoice.getDetalleFacturaCollection().forEach((detalle) -> {
                System.out.println("detalle = " + detalle); 
            }
            );
            
//            JasperPrint jprPrint = JasperFillManager.fillReport(path, parametro, datasource);
//            JasperViewer view = new JasperViewer(jprPrint, false);
//            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//            view.setVisible(true);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(frmfactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
