/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import configs.configs;
import controladores.FacturaJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.hibernate.Session;
import static vistas.inicio.tbdet;
import vistas.inicio;

/**
 *
 * @author matias
 */
public class helpers {
    FacturaJpaController facturaController = new FacturaJpaController();
    private int dato;
    private final int cont;
    private String num="";
    private EntityManagerFactory emf = null;

    public helpers() {
        this.cont = 1;
        this.emf = configs.conexion();
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public void generar(int dato) {
        this.dato = dato;
           if((this.dato>=10000000) || (this.dato<100000000)) 
           {
               int can=cont+this.dato;
               num = "" + can; 
           }
           if((this.dato>=1000000) || (this.dato<10000000)) 
           {
               int can=cont+this.dato;
               num = "0" + can; 
           }
           if((this.dato>=100000) || (this.dato<1000000)) 
           {
               int can=cont+this.dato;
               num = "00" + can; 
           }
           if((this.dato>=10000) || (this.dato<100000)) 
           {
               int can=cont+this.dato;
               num = "000" + can; 
           }
           if((this.dato>=1000) || (this.dato<10000)) 
           {
               int can=cont+this.dato;
               num = "0000" + can; 
           }
           if((this.dato>=100) || (this.dato<1000))
           {
               int can=cont+this.dato;
               num = "00000" + can; 
           }
           if((this.dato>=9) || (this.dato<100)) 
           {
                int can=cont+this.dato;
               num = "000000" + can; 
           }
           if (this.dato<9)
           {
               int can=cont+this.dato;
               num = "0000000" + can; 
           }
          
    }

    public String serie()
    {
        return this.num;
    }

   public void calcular(inicio form) {
        String pre;
        String can;
        double igv = 0;
        double total = 0;
        double subtotal = 0;
        double precio;
        int cantidad;
        double imp = 0.0;

        /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/
        for (int i = 0; i < tbdet.getRowCount(); i++) {
            pre = tbdet.getValueAt(i, 2).toString();
            can = tbdet.getValueAt(i, 3).toString();
            precio = Double.parseDouble(pre);
            cantidad = Integer.parseInt(can);
            imp = precio * cantidad;
            subtotal = subtotal + imp;
            igv = subtotal * 0.18;
            total = subtotal + igv;
            // txtcod.setText(""+Math.rint(c*100)/100)
            tbdet.setValueAt(Math.rint(imp * 100) / 100, i, 4);

        }
        form.txtsubtotal.setText(Double.toString(subtotal));
        form.txtigv.setText("" + Math.rint(igv * 100) / 100);
        form.txttotal.setText("" + Math.rint(total * 100) / 100);

    }
   
    /**
     *
     * @return
     */
    public String GenerateNumber()
     {
        int j;
        try {
       String c = facturaController.getLastNumber();
           
            if(c==null){
                return "00000001";
            }
            else{
                 j=Integer.parseInt(c);
                 generar(j);
                return serie();
            }
        } catch (NumberFormatException e) {
            System.out.println("error = " + e.getMessage());
        }
        return null;
    }
    
    
    public EntityTransaction NewSession(){
        try{
            EntityManager em = getEntityManager();
            return em.getTransaction();
        }catch(Exception e){
            System.out.println("error al generar transaction = " + e.getMessage());
        }
        return null;
    }

}
