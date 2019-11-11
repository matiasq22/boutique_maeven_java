/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controladores.MarcaJpaController;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.util.Collection;
import modelo.Marca;
import modelo.Producto;

/**
 *
 * @author matias
 */
public class MarcaDao {
    private final MarcaJpaController controller;
    private final Marca marca;
    private String message = "";
    public MarcaDao(){
        this.controller = new MarcaJpaController();
        this.marca = new Marca();
    }
    
     public String create(String descripcion, String marca, Collection<Producto> productoCollection) {
        try {
            
            this.marca.setDescripcion(descripcion);
            this.marca.setMarca(marca);
            this.marca.setProductoCollection(productoCollection);
            controller.create(this.marca);
            message = "Marca registrada";
        } catch (Exception e) {
            System.out.println("MarcaDao::create error on save = " + e.getMessage());
            message = "Error al registrar Marca";
        }
        return message;
    }
    
    
    public String update(int id,String descripcion, String marca, Collection<Producto> productoCollection) {
        try {
            this.marca.setId(id);
            this.marca.setDescripcion(descripcion);
            this.marca.setMarca(marca);
            this.marca.setProductoCollection(productoCollection);
            controller.edit(this.marca);
            message = "Marca registrada";
        } catch (Exception e) {
            System.out.println("MarcaDao::update error on save = " + e.getMessage());
            message = "Error al registrar Marca";
        }
        return message;
    }
    
    
    public String remove(int id){
        try {
            Marca mark = controller.findMarca(id);
            if(mark == null)
                message = "No existe una marca con el id " + id;
            controller.destroy(id);
            message = "Marca eliminada";
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            System.out.println("MarcaDao::remove | error = " + e.getMessage());
            message = "Error al eliminar Marca";
        }
        return message;
    }
}
