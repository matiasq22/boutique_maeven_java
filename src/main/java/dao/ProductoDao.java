/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controladores.ProductoJpaController;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.util.Collection;
import modelo.DetalleFactura;
import modelo.Marca;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author matias
 */
public class ProductoDao {
    private final ProductoJpaController controller;
    private final Producto product;
    private String message = "";
    public ProductoDao(){
        this.controller = new ProductoJpaController();
        this.product = new Producto();
    }
    
    public String create(String nombre, String modelo, int precio, int cantidad, Marca marca, Proveedor proveedor, Collection<DetalleFactura> detalleFacturaCollection){
        try {
            product.setNombre(nombre);
            product.setModelo(modelo);
            product.setPrecio(precio);
            product.setCantidad(cantidad);
            product.setMarcaId(marca);
            product.setProveedorId(proveedor);
            product.setDetalleFacturaCollection(detalleFacturaCollection);
            controller.create(product);
            message = "Producto registrado!!";
        } catch (Exception e) {
            System.out.println("ProductoDao::create | error on save = " + e.getMessage());
            message = "Error al guardar producto";
        }
        return message;
    }
    
    public String update(int id, String nombre, String modelo, int precio, int cantidad, Marca marca, Proveedor proveedor, Collection<DetalleFactura> detalleFacturaCollection){
        try {
            product.setId(id);
            product.setNombre(nombre);
            product.setModelo(modelo);
            product.setPrecio(precio);
            product.setCantidad(cantidad);
            product.setMarcaId(marca);
            product.setProveedorId(proveedor);
            product.setDetalleFacturaCollection(detalleFacturaCollection);
            controller.edit(product);
            message = "Producto actualizado!!";
        } catch (Exception e) {
            System.out.println("ProductoDao::update | error on save = " + e.getMessage());
            message = "Error al guardar producto";
        }
        return message;
    }
    
    
       public String remove(int id){
        try {
            Producto producto = controller.findProducto(id);
            if(producto == null)
                message = "No existe un producto con el id " + id;
            controller.destroy(id);
            message = "Producto eliminado";
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            System.out.println("ProductoDao::remove | error = " + e.getMessage());
            message = "Error al eliminar Producto";
        }
        return message;
    }
       
       public String updateStock(int id, int cant) throws Exception{
        try {
            Producto producto = controller.findProducto(id);
            if(producto == null)
                message = "No existe un producto con el id " + id;
            producto.setCantidad(cant);
            controller.edit(producto);
            message = "stock actualizado";
        } catch (IllegalOrphanException | NonexistentEntityException  e) {
            System.out.println("ProductoDao::updateStock | error = " + e.getMessage());
            message = "Error al actualizar Producto";
        }   
        return message;
       }
}
