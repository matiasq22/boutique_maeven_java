/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import configs.configs;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.DetalleFactura;
import modelo.Producto;
import modelo.Factura;

/**
 *
 * @author matias
 */
public class DetalleFacturaJpaController implements Serializable {

    public DetalleFacturaJpaController() {
        this.emf = configs.conexion();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleFactura detalleFactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoId = detalleFactura.getProductoId();
            if (productoId != null) {
                productoId = em.getReference(productoId.getClass(), productoId.getId());
                detalleFactura.setProductoId(productoId);
            }
            Factura facturaId = detalleFactura.getFacturaId();
            if (facturaId != null) {
                facturaId = em.getReference(facturaId.getClass(), facturaId.getId());
                detalleFactura.setFacturaId(facturaId);
            }
            em.persist(detalleFactura);
            if (productoId != null) {
                productoId.getDetalleFacturaCollection().add(detalleFactura);
                productoId = em.merge(productoId);
            }
            if (facturaId != null) {
                facturaId.getDetalleFacturaCollection().add(detalleFactura);
                facturaId = em.merge(facturaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleFactura detalleFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleFactura persistentDetalleFactura = em.find(DetalleFactura.class, detalleFactura.getId());
            Producto productoIdOld = persistentDetalleFactura.getProductoId();
            Producto productoIdNew = detalleFactura.getProductoId();
            Factura facturaIdOld = persistentDetalleFactura.getFacturaId();
            Factura facturaIdNew = detalleFactura.getFacturaId();
            if (productoIdNew != null) {
                productoIdNew = em.getReference(productoIdNew.getClass(), productoIdNew.getId());
                detalleFactura.setProductoId(productoIdNew);
            }
            if (facturaIdNew != null) {
                facturaIdNew = em.getReference(facturaIdNew.getClass(), facturaIdNew.getId());
                detalleFactura.setFacturaId(facturaIdNew);
            }
            detalleFactura = em.merge(detalleFactura);
            if (productoIdOld != null && !productoIdOld.equals(productoIdNew)) {
                productoIdOld.getDetalleFacturaCollection().remove(detalleFactura);
                productoIdOld = em.merge(productoIdOld);
            }
            if (productoIdNew != null && !productoIdNew.equals(productoIdOld)) {
                productoIdNew.getDetalleFacturaCollection().add(detalleFactura);
                productoIdNew = em.merge(productoIdNew);
            }
            if (facturaIdOld != null && !facturaIdOld.equals(facturaIdNew)) {
                facturaIdOld.getDetalleFacturaCollection().remove(detalleFactura);
                facturaIdOld = em.merge(facturaIdOld);
            }
            if (facturaIdNew != null && !facturaIdNew.equals(facturaIdOld)) {
                facturaIdNew.getDetalleFacturaCollection().add(detalleFactura);
                facturaIdNew = em.merge(facturaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleFactura.getId();
                if (findDetalleFactura(id) == null) {
                    throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleFactura detalleFactura;
            try {
                detalleFactura = em.getReference(DetalleFactura.class, id);
                detalleFactura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.", enfe);
            }
            Producto productoId = detalleFactura.getProductoId();
            if (productoId != null) {
                productoId.getDetalleFacturaCollection().remove(detalleFactura);
                productoId = em.merge(productoId);
            }
            Factura facturaId = detalleFactura.getFacturaId();
            if (facturaId != null) {
                facturaId.getDetalleFacturaCollection().remove(detalleFactura);
                facturaId = em.merge(facturaId);
            }
            em.remove(detalleFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleFactura> findDetalleFacturaEntities() {
        return findDetalleFacturaEntities(true, -1, -1);
    }

    public List<DetalleFactura> findDetalleFacturaEntities(int maxResults, int firstResult) {
        return findDetalleFacturaEntities(false, maxResults, firstResult);
    }

    private List<DetalleFactura> findDetalleFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleFactura.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DetalleFactura findDetalleFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleFactura> rt = cq.from(DetalleFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
