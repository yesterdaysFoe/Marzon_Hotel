/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Discounts;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author kurtz
 */
public class DiscountsJpaController implements Serializable {

    public DiscountsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Discounts discounts) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(discounts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Discounts discounts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            discounts = em.merge(discounts);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = discounts.getId();
                if (findDiscounts(id) == null) {
                    throw new NonexistentEntityException("The discounts with id " + id + " no longer exists.");
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
            Discounts discounts;
            try {
                discounts = em.getReference(Discounts.class, id);
                discounts.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discounts with id " + id + " no longer exists.", enfe);
            }
            em.remove(discounts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Discounts> findDiscountsEntities() {
        return findDiscountsEntities(true, -1, -1);
    }

    public List<Discounts> findDiscountsEntities(int maxResults, int firstResult) {
        return findDiscountsEntities(false, maxResults, firstResult);
    }

    private List<Discounts> findDiscountsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Discounts.class));
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

    public Discounts findDiscounts(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Discounts.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscountsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Discounts> rt = cq.from(Discounts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Discounts> findAllActiveDiscounts() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            return em.createQuery("SELECT d FROM Discounts d WHERE d.active = ?1 ORDER BY d.name ASC").setParameter(1, true).getResultList();
        } finally {
            em.close();
        }
    }

}
