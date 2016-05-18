/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.ProductsAndServices;
import entities.TransactionProducts;
import entities.Transactions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class TransactionProductsJpaController implements Serializable {

    public TransactionProductsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionProducts transactionProducts) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductsAndServices productAndServicesId = transactionProducts.getProductAndServicesId();
            if (productAndServicesId != null) {
                productAndServicesId = em.getReference(productAndServicesId.getClass(), productAndServicesId.getId());
                transactionProducts.setProductAndServicesId(productAndServicesId);
            }
            Transactions transactionId = transactionProducts.getTransactionId();
            if (transactionId != null) {
                transactionId = em.getReference(transactionId.getClass(), transactionId.getId());
                transactionProducts.setTransactionId(transactionId);
            }
            em.persist(transactionProducts);
            if (productAndServicesId != null) {
                productAndServicesId.getTransactionProductsCollection().add(transactionProducts);
                productAndServicesId = em.merge(productAndServicesId);
            }
            if (transactionId != null) {
                transactionId.getTransactionProductsCollection().add(transactionProducts);
                transactionId = em.merge(transactionId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionProducts transactionProducts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TransactionProducts persistentTransactionProducts = em.find(TransactionProducts.class, transactionProducts.getId());
            ProductsAndServices productAndServicesIdOld = persistentTransactionProducts.getProductAndServicesId();
            ProductsAndServices productAndServicesIdNew = transactionProducts.getProductAndServicesId();
            Transactions transactionIdOld = persistentTransactionProducts.getTransactionId();
            Transactions transactionIdNew = transactionProducts.getTransactionId();
            if (productAndServicesIdNew != null) {
                productAndServicesIdNew = em.getReference(productAndServicesIdNew.getClass(), productAndServicesIdNew.getId());
                transactionProducts.setProductAndServicesId(productAndServicesIdNew);
            }
            if (transactionIdNew != null) {
                transactionIdNew = em.getReference(transactionIdNew.getClass(), transactionIdNew.getId());
                transactionProducts.setTransactionId(transactionIdNew);
            }
            transactionProducts = em.merge(transactionProducts);
            if (productAndServicesIdOld != null && !productAndServicesIdOld.equals(productAndServicesIdNew)) {
                productAndServicesIdOld.getTransactionProductsCollection().remove(transactionProducts);
                productAndServicesIdOld = em.merge(productAndServicesIdOld);
            }
            if (productAndServicesIdNew != null && !productAndServicesIdNew.equals(productAndServicesIdOld)) {
                productAndServicesIdNew.getTransactionProductsCollection().add(transactionProducts);
                productAndServicesIdNew = em.merge(productAndServicesIdNew);
            }
            if (transactionIdOld != null && !transactionIdOld.equals(transactionIdNew)) {
                transactionIdOld.getTransactionProductsCollection().remove(transactionProducts);
                transactionIdOld = em.merge(transactionIdOld);
            }
            if (transactionIdNew != null && !transactionIdNew.equals(transactionIdOld)) {
                transactionIdNew.getTransactionProductsCollection().add(transactionProducts);
                transactionIdNew = em.merge(transactionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactionProducts.getId();
                if (findTransactionProducts(id) == null) {
                    throw new NonexistentEntityException("The transactionProducts with id " + id + " no longer exists.");
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
            TransactionProducts transactionProducts;
            try {
                transactionProducts = em.getReference(TransactionProducts.class, id);
                transactionProducts.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionProducts with id " + id + " no longer exists.", enfe);
            }
            ProductsAndServices productAndServicesId = transactionProducts.getProductAndServicesId();
            if (productAndServicesId != null) {
                productAndServicesId.getTransactionProductsCollection().remove(transactionProducts);
                productAndServicesId = em.merge(productAndServicesId);
            }
            Transactions transactionId = transactionProducts.getTransactionId();
            if (transactionId != null) {
                transactionId.getTransactionProductsCollection().remove(transactionProducts);
                transactionId = em.merge(transactionId);
            }
            em.remove(transactionProducts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TransactionProducts> findTransactionProductsEntities() {
        return findTransactionProductsEntities(true, -1, -1);
    }

    public List<TransactionProducts> findTransactionProductsEntities(int maxResults, int firstResult) {
        return findTransactionProductsEntities(false, maxResults, firstResult);
    }

    private List<TransactionProducts> findTransactionProductsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionProducts.class));
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

    public TransactionProducts findTransactionProducts(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionProducts.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionProducts> rt = cq.from(TransactionProducts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Object[]> getServiceReports(){
        EntityManager em = getEntityManager();
        try {
            return em.createNativeQuery("SELECT ps.name, ps.details,  tp.price, sum(tp.qty) FROM `transaction_products` tp INNER JOIN products_and_services ps ON ps.id = product_and_services_id WHERE ps.category = 'Hotel Service' group by product_and_services_id")
                    .getResultList();
        } finally {
            em.close();
        }
        
    }
    
}
