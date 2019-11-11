/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controladores.DetalleFacturaJpaController;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.Producto;

/**
 *
 * @author matias
 */
public class DetalleFacDao {
private DetalleFactura detalle = new DetalleFactura();
private DetalleFacturaJpaController controller = new DetalleFacturaJpaController();
private String message = "";
public DetalleFacDao(){
}

public String create(int cantidad, double descuento, Factura factura, Producto producto, double total) {
        try {
            detalle.setCantidad(cantidad);
            detalle.setDescuento(descuento);
            detalle.setFacturaId(factura);
            detalle.setProductoId(producto);
            detalle.setTotalventa(total);
            controller.create(detalle);
            message = "Detalle Factura guardada correctamente";
        } catch (Exception e) {
            message  = "No se pudo guardar el Detalle Factura ";
            System.out.println("DetalleFacturaDao::create error on save = " + e.getMessage());
        }
        return message;
    }
    
    
    public String update(int id, int cantidad, double descuento, Factura factura, Producto producto, double total) {
        try {
            detalle.setId(id);
            detalle.setCantidad(cantidad);
            detalle.setDescuento(descuento);
            detalle.setFacturaId(factura);
            detalle.setProductoId(producto);
            detalle.setTotalventa(total);
            controller.create(detalle);
            message = "Detalle Factura actualizado correctamente";
        } catch (Exception e) {
            message  = "No se pudo actualizar el Detalle Factura ";
            System.out.println("DetalleFacturaDao::update error on save = " + e.getMessage());
        }
        return message;
    }
    
}
