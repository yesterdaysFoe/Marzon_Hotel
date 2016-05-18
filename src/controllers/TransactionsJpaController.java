/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.TransactionProducts;
import java.util.ArrayList;
import java.util.Collection;
import entities.TransactionPayments;
import entities.Transactions;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class TransactionsJpaController implements Serializable {

    public TransactionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Transactions transactions) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transactions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Transactions transactions) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(transactions);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactions.getId();
                if (findTransactions(id) == null) {
                    throw new NonexistentEntityException("The transactions with id " + id + " no longer exists.");
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
            Transactions transactions;
            try {
                transactions = em.getReference(Transactions.class, id);
                transactions.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactions with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TransactionProducts> transactionProductsCollectionOrphanCheck = transactions.getTransactionProductsCollection();
            for (TransactionProducts transactionProductsCollectionOrphanCheckTransactionProducts : transactionProductsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transactions (" + transactions + ") cannot be destroyed since the TransactionProducts " + transactionProductsCollectionOrphanCheckTransactionProducts + " in its transactionProductsCollection field has a non-nullable transactionId field.");
            }
            Collection<TransactionPayments> transactionPaymentsCollectionOrphanCheck = transactions.getTransactionPaymentsCollection();
            for (TransactionPayments transactionPaymentsCollectionOrphanCheckTransactionPayments : transactionPaymentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Transactions (" + transactions + ") cannot be destroyed since the TransactionPayments " + transactionPaymentsCollectionOrphanCheckTransactionPayments + " in its transactionPaymentsCollection field has a non-nullable transactionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(transactions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Transactions> findTransactionsEntities() {
        return findTransactionsEntities(true, -1, -1);
    }

    public List<Transactions> findTransactionsEntities(int maxResults, int firstResult) {
        return findTransactionsEntities(false, maxResults, firstResult);
    }

    private List<Transactions> findTransactionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Transactions.class));
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

    public Transactions findTransactions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Transactions.class, id);
        } finally {
            em.close();
        }
    }

    public Transactions findTransactionsEager(Integer id) {
        EntityManager em = getEntityManager();
        try {
            Transactions t = em.find(Transactions.class, id);
            t.getTransactionPaymentsCollection().size();
            t.getTransactionRoomsCollection().size();
            t.getTransactionProductsCollection().size();
            return t;
        } finally {
            em.close();
        }
    }

    public int getTransactionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Transactions> rt = cq.from(Transactions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Transactions> findAllIncompleteTransaction() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            return em.createQuery("SELECT t FROM Transactions t WHERE t.checkedOut = ?1").setParameter(1, false).getResultList();
        } finally {
            em.close();
        }
    }

    public Collection<Object[]> findByDates(Date date, Date date0) {
        return getEntityManager().createNativeQuery("SELECT r.name, t.customer_first_name, t.customer_last_name, tr.arrival_date, tr.departure_date, t.total_restaurant_bill, t.total_hotel_bill "
                + "FROM `transaction_rooms` tr "
                + "INNER JOIN transactions t ON t.id = tr.transaction_id "
                + "INNER JOIN rooms r ON r.id = tr.room "
                + "WHERE t.checked_out = ?3 AND (t.date BETWEEN ?1 AND ?2)")
                .setParameter(1, date)
                .setParameter(2, date0)
                .setParameter(3, true)
                .getResultList();
    }

}
