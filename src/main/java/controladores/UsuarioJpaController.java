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
import modelo.Usuario;

/**
 *
 * @author matias
 */
public class UsuarioJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public UsuarioJpaController() {
        this.emf = configs.conexion();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("El usuario con id  " + id + " no existe.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            System.out.println("query = " + q.getResultList());
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }

    public Usuario findByUsername(String username) {
        EntityManager em = getEntityManager();
        Usuario user;
        Query query = em.createNamedQuery("Usuario.findByUsername");
        query.setParameter("username", username);
        try {
            user = (Usuario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
            em.close();
            return null;
        }
        em.close();
        return user;
    }
  
    public List<Usuario> search(String nombre) {
        EntityManager em = getEntityManager();
        List<Usuario> users;
//        String queryname = (!nombre.equals("")) ? "Usuario.findByNombre" : "Usuario.findAll";
//        System.out.println("queryname = " + queryname);
        Query query = em.createNamedQuery("Usuario.findByNombre");
//        if(!nombre.equals("")){
            query.setParameter("nombre", "%"+ nombre + "%");
//        }
        try {
            users =  query.getResultList();
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
            em.close();
            return null;
        }
        em.close();
        return users;
    }

    public Usuario login(String usuario, String pass) {
        EntityManager em = getEntityManager();
        try {
            Usuario user;
            user = this.findByUsername(usuario);
            if (user.getPass() == null ? pass != null : !user.getPass().equals(pass)) {
                em.close();
                return null;
            }
            em.close();
            close();
            return user;
        } catch (Exception e) {
            System.out.println("error = " + e.getMessage());
        }
        return null;
    }

}