/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import configs.configs;
import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Marca;
import modelo.Proveedor;
import modelo.DetalleFactura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Producto;

/**
 *
 * @author matias
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController() {
        this.emf = configs.conexion();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDetalleFacturaCollection() == null) {
            producto.setDetalleFacturaCollection(new ArrayList<DetalleFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca marcaId = producto.getMarcaId();
            if (marcaId != null) {
                marcaId = em.getReference(marcaId.getClass(), marcaId.getId());
                producto.setMarcaId(marcaId);
            }
            Proveedor proveedorId = producto.getProveedorId();
            if (proveedorId != null) {
                proveedorId = em.getReference(proveedorId.getClass(), proveedorId.getId());
                producto.setProveedorId(proveedorId);
            }
            Collection<DetalleFactura> attachedDetalleFacturaCollection = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaCollectionDetalleFacturaToAttach : producto.getDetalleFacturaCollection()) {
                detalleFacturaCollectionDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionDetalleFacturaToAttach.getClass(), detalleFacturaCollectionDetalleFacturaToAttach.getId());
                attachedDetalleFacturaCollection.add(detalleFacturaCollectionDetalleFacturaToAttach);
            }
            producto.setDetalleFacturaCollection(attachedDetalleFacturaCollection);
            em.persist(producto);
            if (marcaId != null) {
                marcaId.getProductoCollection().add(producto);
                marcaId = em.merge(marcaId);
            }
            if (proveedorId != null) {
                proveedorId.getProductoCollection().add(producto);
                proveedorId = em.merge(proveedorId);
            }
            for (DetalleFactura detalleFacturaCollectionDetalleFactura : producto.getDetalleFacturaCollection()) {
                Producto oldProductoIdOfDetalleFacturaCollectionDetalleFactura = detalleFacturaCollectionDetalleFactura.getProductoId();
                detalleFacturaCollectionDetalleFactura.setProductoId(producto);
                detalleFacturaCollectionDetalleFactura = em.merge(detalleFacturaCollectionDetalleFactura);
                if (oldProductoIdOfDetalleFacturaCollectionDetalleFactura != null) {
                    oldProductoIdOfDetalleFacturaCollectionDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionDetalleFactura);
                    oldProductoIdOfDetalleFacturaCollectionDetalleFactura = em.merge(oldProductoIdOfDetalleFacturaCollectionDetalleFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            Marca marcaIdOld = persistentProducto.getMarcaId();
            Marca marcaIdNew = producto.getMarcaId();
            Proveedor proveedorIdOld = persistentProducto.getProveedorId();
            Proveedor proveedorIdNew = producto.getProveedorId();
            Collection<DetalleFactura> detalleFacturaCollectionOld = persistentProducto.getDetalleFacturaCollection();
            Collection<DetalleFactura> detalleFacturaCollectionNew = producto.getDetalleFacturaCollection();
            List<String> illegalOrphanMessages = null;
            for (DetalleFactura detalleFacturaCollectionOldDetalleFactura : detalleFacturaCollectionOld) {
                if (!detalleFacturaCollectionNew.contains(detalleFacturaCollectionOldDetalleFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleFactura " + detalleFacturaCollectionOldDetalleFactura + " since its productoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (marcaIdNew != null) {
                marcaIdNew = em.getReference(marcaIdNew.getClass(), marcaIdNew.getId());
                producto.setMarcaId(marcaIdNew);
            }
            if (proveedorIdNew != null) {
                proveedorIdNew = em.getReference(proveedorIdNew.getClass(), proveedorIdNew.getId());
                producto.setProveedorId(proveedorIdNew);
            }
            Collection<DetalleFactura> attachedDetalleFacturaCollectionNew = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaCollectionNewDetalleFacturaToAttach : detalleFacturaCollectionNew) {
                detalleFacturaCollectionNewDetalleFacturaToAttach = em.getReference(detalleFacturaCollectionNewDetalleFacturaToAttach.getClass(), detalleFacturaCollectionNewDetalleFacturaToAttach.getId());
                attachedDetalleFacturaCollectionNew.add(detalleFacturaCollectionNewDetalleFacturaToAttach);
            }
            detalleFacturaCollectionNew = attachedDetalleFacturaCollectionNew;
            producto.setDetalleFacturaCollection(detalleFacturaCollectionNew);
            producto = em.merge(producto);
            if (marcaIdOld != null && !marcaIdOld.equals(marcaIdNew)) {
                marcaIdOld.getProductoCollection().remove(producto);
                marcaIdOld = em.merge(marcaIdOld);
            }
            if (marcaIdNew != null && !marcaIdNew.equals(marcaIdOld)) {
                marcaIdNew.getProductoCollection().add(producto);
                marcaIdNew = em.merge(marcaIdNew);
            }
            if (proveedorIdOld != null && !proveedorIdOld.equals(proveedorIdNew)) {
                proveedorIdOld.getProductoCollection().remove(producto);
                proveedorIdOld = em.merge(proveedorIdOld);
            }
            if (proveedorIdNew != null && !proveedorIdNew.equals(proveedorIdOld)) {
                proveedorIdNew.getProductoCollection().add(producto);
                proveedorIdNew = em.merge(proveedorIdNew);
            }
            for (DetalleFactura detalleFacturaCollectionNewDetalleFactura : detalleFacturaCollectionNew) {
                if (!detalleFacturaCollectionOld.contains(detalleFacturaCollectionNewDetalleFactura)) {
                    Producto oldProductoIdOfDetalleFacturaCollectionNewDetalleFactura = detalleFacturaCollectionNewDetalleFactura.getProductoId();
                    detalleFacturaCollectionNewDetalleFactura.setProductoId(producto);
                    detalleFacturaCollectionNewDetalleFactura = em.merge(detalleFacturaCollectionNewDetalleFactura);
                    if (oldProductoIdOfDetalleFacturaCollectionNewDetalleFactura != null && !oldProductoIdOfDetalleFacturaCollectionNewDetalleFactura.equals(producto)) {
                        oldProductoIdOfDetalleFacturaCollectionNewDetalleFactura.getDetalleFacturaCollection().remove(detalleFacturaCollectionNewDetalleFactura);
                        oldProductoIdOfDetalleFacturaCollectionNewDetalleFactura = em.merge(oldProductoIdOfDetalleFacturaCollectionNewDetalleFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetalleFactura> detalleFacturaCollectionOrphanCheck = producto.getDetalleFacturaCollection();
            for (DetalleFactura detalleFacturaCollectionOrphanCheckDetalleFactura : detalleFacturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetalleFactura " + detalleFacturaCollectionOrphanCheckDetalleFactura + " in its detalleFacturaCollection field has a non-nullable productoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Marca marcaId = producto.getMarcaId();
            if (marcaId != null) {
                marcaId.getProductoCollection().remove(producto);
                marcaId = em.merge(marcaId);
            }
            Proveedor proveedorId = producto.getProveedorId();
            if (proveedorId != null) {
                proveedorId.getProductoCollection().remove(producto);
                proveedorId = em.merge(proveedorId);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Producto> search(String nombre) {
        EntityManager em = getEntityManager();
        List<Producto> productos;
        Query query = em.createNamedQuery("Producto.findByNombre");
            query.setParameter("nombre", "%"+ nombre + "%");
        try {
            productos =  query.getResultList();
            em.close();
            return productos;
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
            em.close();
            return null;
        }
    }
    
    public void descontarstock(String id, String cantidad) throws NonexistentEntityException, Exception {
       EntityManager em  = getEntityManager();
       int idp = Integer.parseInt(id);
       Producto producto; 
       try {
         producto = findProducto(idp);
         producto.setCantidad(Integer.parseInt(cantidad));
         edit(producto);
//         return producto;
        } catch (NumberFormatException e) {
            System.out.println("error al descontar stock = " + e.getMessage());
        }
//        return null;
    }
    
}
