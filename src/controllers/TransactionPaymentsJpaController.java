/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.TransactionPayments;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Transactions;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class TransactionPaymentsJpaController implements Serializable {

    public TransactionPaymentsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionPayments transactionPayments) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transactions transactionId = transactionPayments.getTransactionId();
            if (transactionId != null) {
                transactionId = em.getReference(transactionId.getClass(), transactionId.getId());
                transactionPayments.setTransactionId(transactionId);
            }
            em.persist(transactionPayments);
            if (transactionId != null) {
                transactionId.getTransactionPaymentsCollection().add(transactionPayments);
                transactionId = em.merge(transactionId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionPayments transactionPayments) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TransactionPayments persistentTransactionPayments = em.find(TransactionPayments.class, transactionPayments.getId());
            Transactions transactionIdOld = persistentTransactionPayments.getTransactionId();
            Transactions transactionIdNew = transactionPayments.getTransactionId();
            if (transactionIdNew != null) {
                transactionIdNew = em.getReference(transactionIdNew.getClass(), transactionIdNew.getId());
                transactionPayments.setTransactionId(transactionIdNew);
            }
            transactionPayments = em.merge(transactionPayments);
            if (transactionIdOld != null && !transactionIdOld.equals(transactionIdNew)) {
                transactionIdOld.getTransactionPaymentsCollection().remove(transactionPayments);
                transactionIdOld = em.merge(transactionIdOld);
            }
            if (transactionIdNew != null && !transactionIdNew.equals(transactionIdOld)) {
                transactionIdNew.getTransactionPaymentsCollection().add(transactionPayments);
                transactionIdNew = em.merge(transactionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactionPayments.getId();
                if (findTransactionPayments(id) == null) {
                    throw new NonexistentEntityException("The transactionPayments with id " + id + " no longer exists.");
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
            TransactionPayments transactionPayments;
            try {
                transactionPayments = em.getReference(TransactionPayments.class, id);
                transactionPayments.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionPayments with id " + id + " no longer exists.", enfe);
            }
            Transactions transactionId = transactionPayments.getTransactionId();
            if (transactionId != null) {
                transactionId.getTransactionPaymentsCollection().remove(transactionPayments);
                transactionId = em.merge(transactionId);
            }
            em.remove(transactionPayments);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TransactionPayments> findTransactionPaymentsEntities() {
        return findTransactionPaymentsEntities(true, -1, -1);
    }

    public List<TransactionPayments> findTransactionPaymentsEntities(int maxResults, int firstResult) {
        return findTransactionPaymentsEntities(false, maxResults, firstResult);
    }

    private List<TransactionPayments> findTransactionPaymentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionPayments.class));
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

    public TransactionPayments findTransactionPayments(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionPayments.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionPaymentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionPayments> rt = cq.from(TransactionPayments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TransactionPayments> findTransactionPaymentsById(Integer id) {
        return getEntityManager().createQuery("SELECT tp FROM TransactionPayments tp WHERE tp.transactionId.id = ?1").setParameter(1, id).getResultList();
    }

    public Collection<? extends TransactionPayments> findByDates(Date date, Date date0) {
        return getEntityManager().createQuery("SELECT tp FROM TransactionPayments tp WHERE tp.transactionId.id != 'NULL' AND (tp.dateOfPayment BETWEEN ?1 AND ?2)")
                .setParameter(1, date)
                .setParameter(2, date0)
                .getResultList();
    }

}
