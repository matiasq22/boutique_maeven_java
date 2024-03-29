/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.mycompany.boutique.exceptions.IllegalOrphanException;
import com.mycompany.boutique.exceptions.NonexistentEntityException;
import configs.configs;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Factura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Cliente;

/**
 *
 * @author matias
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
        this.emf = configs.conexion();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getFacturaCollection() == null) {
            cliente.setFacturaCollection(new ArrayList<Factura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Factura> attachedFacturaCollection = new ArrayList<Factura>();
            for (Factura facturaCollectionFacturaToAttach : cliente.getFacturaCollection()) {
                facturaCollectionFacturaToAttach = em.getReference(facturaCollectionFacturaToAttach.getClass(), facturaCollectionFacturaToAttach.getId());
                attachedFacturaCollection.add(facturaCollectionFacturaToAttach);
            }
            cliente.setFacturaCollection(attachedFacturaCollection);
            em.persist(cliente);
            for (Factura facturaCollectionFactura : cliente.getFacturaCollection()) {
                Cliente oldClienteIdOfFacturaCollectionFactura = facturaCollectionFactura.getClienteId();
                facturaCollectionFactura.setClienteId(cliente);
                facturaCollectionFactura = em.merge(facturaCollectionFactura);
                if (oldClienteIdOfFacturaCollectionFactura != null) {
                    oldClienteIdOfFacturaCollectionFactura.getFacturaCollection().remove(facturaCollectionFactura);
                    oldClienteIdOfFacturaCollectionFactura = em.merge(oldClienteIdOfFacturaCollectionFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId());
//            Collection<Factura> facturaCollectionOld = persistentCliente.getFacturaCollection();
//            Collection<Factura> facturaCollectionNew = cliente.getFacturaCollection();
//            List<String> illegalOrphanMessages = null;
//            for (Factura facturaCollectionOldFactura : facturaCollectionOld) {
//                if (!facturaCollectionNew.contains(facturaCollectionOldFactura)) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("You must retain Factura " + facturaCollectionOldFactura + " since its clienteId field is not nullable.");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
//            Collection<Factura> attachedFacturaCollectionNew = new ArrayList<Factura>();
//            for (Factura facturaCollectionNewFacturaToAttach : facturaCollectionNew) {
//                facturaCollectionNewFacturaToAttach = em.getReference(facturaCollectionNewFacturaToAttach.getClass(), facturaCollectionNewFacturaToAttach.getId());
//                attachedFacturaCollectionNew.add(facturaCollectionNewFacturaToAttach);
//            }
//            facturaCollectionNew = attachedFacturaCollectionNew;
//            cliente.setFacturaCollection(facturaCollectionNew);
//            cliente = em.merge(cliente);
//            for (Factura facturaCollectionNewFactura : facturaCollectionNew) {
//                if (!facturaCollectionOld.contains(facturaCollectionNewFactura)) {
//                    Cliente oldClienteIdOfFacturaCollectionNewFactura = facturaCollectionNewFactura.getClienteId();
//                    facturaCollectionNewFactura.setClienteId(cliente);
//                    facturaCollectionNewFactura = em.merge(facturaCollectionNewFactura);
//                    if (oldClienteIdOfFacturaCollectionNewFactura != null && !oldClienteIdOfFacturaCollectionNewFactura.equals(cliente)) {
//                        oldClienteIdOfFacturaCollectionNewFactura.getFacturaCollection().remove(facturaCollectionNewFactura);
//                        oldClienteIdOfFacturaCollectionNewFactura = em.merge(oldClienteIdOfFacturaCollectionNewFactura);
//                    }
//                }
//            }
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getId();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Factura> facturaCollectionOrphanCheck = cliente.getFacturaCollection();
            for (Factura facturaCollectionOrphanCheckFactura : facturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Factura " + facturaCollectionOrphanCheckFactura + " in its facturaCollection field has a non-nullable clienteId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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
    
    public List<Cliente> search(String nombre) {
        EntityManager em = getEntityManager();
        List<Cliente> clients;
        Query query = em.createNamedQuery("Cliente.findByNombre");
            query.setParameter("nombre", "%"+ nombre + "%");
        try {
            clients =  query.getResultList();
            em.close();
            return clients;
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
            em.close();
            return null;
        }
    }

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
