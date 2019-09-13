/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configs;
   
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matias
 */
public class configs {
   public static EntityManagerFactory emf;
    public static EntityManagerFactory conexion(){
        return configs.emf = Persistence.createEntityManagerFactory("conexion");
        }
    
}
