/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.PaymentNotification;
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
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class PaymentNotificationJpaController implements Serializable {

    public PaymentNotificationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaymentNotification paymentNotification) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(paymentNotification);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaymentNotification paymentNotification) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paymentNotification = em.merge(paymentNotification);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paymentNotification.getPayId();
                if (findPaymentNotification(id) == null) {
                    throw new NonexistentEntityException("The paymentNotification with id " + id + " no longer exists.");
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
            PaymentNotification paymentNotification;
            try {
                paymentNotification = em.getReference(PaymentNotification.class, id);
                paymentNotification.getPayId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paymentNotification with id " + id + " no longer exists.", enfe);
            }
            em.remove(paymentNotification);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaymentNotification> findPaymentNotificationEntities() {
        return findPaymentNotificationEntities(true, -1, -1);
    }

    public List<PaymentNotification> findPaymentNotificationEntities(int maxResults, int firstResult) {
        return findPaymentNotificationEntities(false, maxResults, firstResult);
    }

    private List<PaymentNotification> findPaymentNotificationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaymentNotification.class));
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

    public PaymentNotification findPaymentNotification(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaymentNotification.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentNotificationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaymentNotification> rt = cq.from(PaymentNotification.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
