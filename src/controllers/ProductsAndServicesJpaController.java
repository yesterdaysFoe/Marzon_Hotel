/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import entities.ProductsAndServices;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.TransactionProducts;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class ProductsAndServicesJpaController implements Serializable {

    public ProductsAndServicesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductsAndServices productsAndServices) {
        if (productsAndServices.getTransactionProductsCollection() == null) {
            productsAndServices.setTransactionProductsCollection(new ArrayList<TransactionProducts>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TransactionProducts> attachedTransactionProductsCollection = new ArrayList<TransactionProducts>();
            for (TransactionProducts transactionProductsCollectionTransactionProductsToAttach : productsAndServices.getTransactionProductsCollection()) {
                transactionProductsCollectionTransactionProductsToAttach = em.getReference(transactionProductsCollectionTransactionProductsToAttach.getClass(), transactionProductsCollectionTransactionProductsToAttach.getId());
                attachedTransactionProductsCollection.add(transactionProductsCollectionTransactionProductsToAttach);
            }
            productsAndServices.setTransactionProductsCollection(attachedTransactionProductsCollection);
            em.persist(productsAndServices);
            for (TransactionProducts transactionProductsCollectionTransactionProducts : productsAndServices.getTransactionProductsCollection()) {
                ProductsAndServices oldProductAndServicesIdOfTransactionProductsCollectionTransactionProducts = transactionProductsCollectionTransactionProducts.getProductAndServicesId();
                transactionProductsCollectionTransactionProducts.setProductAndServicesId(productsAndServices);
                transactionProductsCollectionTransactionProducts = em.merge(transactionProductsCollectionTransactionProducts);
                if (oldProductAndServicesIdOfTransactionProductsCollectionTransactionProducts != null) {
                    oldProductAndServicesIdOfTransactionProductsCollectionTransactionProducts.getTransactionProductsCollection().remove(transactionProductsCollectionTransactionProducts);
                    oldProductAndServicesIdOfTransactionProductsCollectionTransactionProducts = em.merge(oldProductAndServicesIdOfTransactionProductsCollectionTransactionProducts);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductsAndServices productsAndServices) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductsAndServices persistentProductsAndServices = em.find(ProductsAndServices.class, productsAndServices.getId());
            Collection<TransactionProducts> transactionProductsCollectionOld = persistentProductsAndServices.getTransactionProductsCollection();
            Collection<TransactionProducts> transactionProductsCollectionNew = productsAndServices.getTransactionProductsCollection();
            List<String> illegalOrphanMessages = null;
            for (TransactionProducts transactionProductsCollectionOldTransactionProducts : transactionProductsCollectionOld) {
                if (!transactionProductsCollectionNew.contains(transactionProductsCollectionOldTransactionProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransactionProducts " + transactionProductsCollectionOldTransactionProducts + " since its productAndServicesId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TransactionProducts> attachedTransactionProductsCollectionNew = new ArrayList<TransactionProducts>();
            for (TransactionProducts transactionProductsCollectionNewTransactionProductsToAttach : transactionProductsCollectionNew) {
                transactionProductsCollectionNewTransactionProductsToAttach = em.getReference(transactionProductsCollectionNewTransactionProductsToAttach.getClass(), transactionProductsCollectionNewTransactionProductsToAttach.getId());
                attachedTransactionProductsCollectionNew.add(transactionProductsCollectionNewTransactionProductsToAttach);
            }
            transactionProductsCollectionNew = attachedTransactionProductsCollectionNew;
            productsAndServices.setTransactionProductsCollection(transactionProductsCollectionNew);
            productsAndServices = em.merge(productsAndServices);
            for (TransactionProducts transactionProductsCollectionNewTransactionProducts : transactionProductsCollectionNew) {
                if (!transactionProductsCollectionOld.contains(transactionProductsCollectionNewTransactionProducts)) {
                    ProductsAndServices oldProductAndServicesIdOfTransactionProductsCollectionNewTransactionProducts = transactionProductsCollectionNewTransactionProducts.getProductAndServicesId();
                    transactionProductsCollectionNewTransactionProducts.setProductAndServicesId(productsAndServices);
                    transactionProductsCollectionNewTransactionProducts = em.merge(transactionProductsCollectionNewTransactionProducts);
                    if (oldProductAndServicesIdOfTransactionProductsCollectionNewTransactionProducts != null && !oldProductAndServicesIdOfTransactionProductsCollectionNewTransactionProducts.equals(productsAndServices)) {
                        oldProductAndServicesIdOfTransactionProductsCollectionNewTransactionProducts.getTransactionProductsCollection().remove(transactionProductsCollectionNewTransactionProducts);
                        oldProductAndServicesIdOfTransactionProductsCollectionNewTransactionProducts = em.merge(oldProductAndServicesIdOfTransactionProductsCollectionNewTransactionProducts);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productsAndServices.getId();
                if (findProductsAndServices(id) == null) {
                    throw new NonexistentEntityException("The productsAndServices with id " + id + " no longer exists.");
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
            ProductsAndServices productsAndServices;
            try {
                productsAndServices = em.getReference(ProductsAndServices.class, id);
                productsAndServices.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productsAndServices with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TransactionProducts> transactionProductsCollectionOrphanCheck = productsAndServices.getTransactionProductsCollection();
            for (TransactionProducts transactionProductsCollectionOrphanCheckTransactionProducts : transactionProductsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductsAndServices (" + productsAndServices + ") cannot be destroyed since the TransactionProducts " + transactionProductsCollectionOrphanCheckTransactionProducts + " in its transactionProductsCollection field has a non-nullable productAndServicesId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(productsAndServices);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductsAndServices> findProductsAndServicesEntities() {
        return findProductsAndServicesEntities(true, -1, -1);
    }

    public List<ProductsAndServices> findProductsAndServicesEntities(int maxResults, int firstResult) {
        return findProductsAndServicesEntities(false, maxResults, firstResult);
    }

    private List<ProductsAndServices> findProductsAndServicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductsAndServices.class));
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

    public ProductsAndServices findProductsAndServices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductsAndServices.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductsAndServicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductsAndServices> rt = cq.from(ProductsAndServices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TransactionProducts> findTransactionProductsById(Integer id) {
        return getEntityManager().createQuery("SELECT tp FROM TransactionProducts tp WHERE tp.transactionId.id = ?1").setParameter(1, id).getResultList();
    }

    public List<ProductsAndServices> findTransactionType(String category, int firstResult, int maxResult) {
        return getEntityManager().createQuery("SELECT ps FROM ProductsAndServices ps WHERE ps.category = ?1 ORDER BY ps.name")
                .setParameter(1, category)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult).getResultList();
    }

    public List<ProductsAndServices> findTransactionTypeWithKey(String category, String keyword, int firstResult, int maxResult) {
        return getEntityManager().createNativeQuery("SELECT * FROM products_and_services ps WHERE ps.`category` = :category AND (ps.`food_category` LIKE :foodCategory OR ps.`food_type` LIKE :foodType) ORDER BY ps.name", ProductsAndServices.class)
                .setParameter("category", category)
                .setFirstResult(firstResult)
                .setParameter("foodCategory", "%" + keyword + "%")
                .setParameter("foodType", "%" + keyword + "%")
                .setMaxResults(maxResult).getResultList();
    }

    public List<ProductsAndServices> findTransactionTypeOrderByFoodCategory(String category, int firstResult, int maxResult) {
        return getEntityManager().createQuery("SELECT ps FROM ProductsAndServices ps WHERE ps.category = ?1 ORDER BY ps.foodCategory, ps.foodType")
                .setParameter(1, category)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult).getResultList();
    }
    
     public List<ProductsAndServices> findByCategory(String category) {
        return getEntityManager().createQuery("SELECT ps FROM ProductsAndServices ps WHERE ps.category = ?1 ORDER BY ps.foodCategory, ps.foodType")
                .setParameter(1, category).getResultList();
    }

}
