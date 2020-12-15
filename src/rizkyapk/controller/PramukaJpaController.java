/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rizkyapk.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import rizkyapk.controller.exceptions.NonexistentEntityException;
import rizkyapk.controller.exceptions.PreexistingEntityException;
import rizkyapk.models.Pramuka;

/**
 *
 * @author NANDA NAJWAN NOOR
 */
public class PramukaJpaController implements Serializable {

    public PramukaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pramuka pramuka) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pramuka);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPramuka(pramuka.getNta()) != null) {
                throw new PreexistingEntityException("Pramuka " + pramuka + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pramuka pramuka) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pramuka = em.merge(pramuka);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pramuka.getNta();
                if (findPramuka(id) == null) {
                    throw new NonexistentEntityException("The pramuka with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pramuka pramuka;
            try {
                pramuka = em.getReference(Pramuka.class, id);
                pramuka.getNta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pramuka with id " + id + " no longer exists.", enfe);
            }
            em.remove(pramuka);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pramuka> findPramukaEntities() {
        return findPramukaEntities(true, -1, -1);
    }

    public List<Pramuka> findPramukaEntities(int maxResults, int firstResult) {
        return findPramukaEntities(false, maxResults, firstResult);
    }

    private List<Pramuka> findPramukaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pramuka.class));
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

    public Pramuka findPramuka(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pramuka.class, id);
        } finally {
            em.close();
        }
    }

    public int getPramukaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pramuka> rt = cq.from(Pramuka.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
