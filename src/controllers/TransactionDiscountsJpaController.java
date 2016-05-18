/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.TransactionDiscounts;
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
public class TransactionDiscountsJpaController implements Serializable {

    public TransactionDiscountsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionDiscounts transactionDiscounts) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transactionDiscounts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionDiscounts transactionDiscounts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transactionDiscounts = em.merge(transactionDiscounts);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactionDiscounts.getId();
                if (findTransactionDiscounts(id) == null) {
                    throw new NonexistentEntityException("The transactionDiscounts with id " + id + " no longer exists.");
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
            TransactionDiscounts transactionDiscounts;
            try {
                transactionDiscounts = em.getReference(TransactionDiscounts.class, id);
                transactionDiscounts.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionDiscounts with id " + id + " no longer exists.", enfe);
            }
            em.remove(transactionDiscounts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TransactionDiscounts> findTransactionDiscountsEntities() {
        return findTransactionDiscountsEntities(true, -1, -1);
    }

    public List<TransactionDiscounts> findTransactionDiscountsEntities(int maxResults, int firstResult) {
        return findTransactionDiscountsEntities(false, maxResults, firstResult);
    }

    private List<TransactionDiscounts> findTransactionDiscountsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionDiscounts.class));
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

    public TransactionDiscounts findTransactionDiscounts(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionDiscounts.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionDiscountsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionDiscounts> rt = cq.from(TransactionDiscounts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<TransactionDiscounts> findTransactionDiscountById(Integer id) {
        return getEntityManager().createQuery("SELECT td FROM TransactionDiscounts td WHERE td.transactionId.id = ?1").setParameter(1, id).getResultList();
    }
    
}
