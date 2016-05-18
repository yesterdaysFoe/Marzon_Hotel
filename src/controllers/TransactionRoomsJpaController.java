package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.TransactionRooms;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Transactions;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class TransactionRoomsJpaController implements Serializable {

    public TransactionRoomsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionRooms transactionRooms) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Transactions transactionId = transactionRooms.getTransactionId();
            if (transactionId != null) {
                transactionId = em.getReference(transactionId.getClass(), transactionId.getId());
                transactionRooms.setTransactionId(transactionId);
            }
            em.persist(transactionRooms);
            if (transactionId != null) {
                transactionId.getTransactionRoomsCollection().add(transactionRooms);
                transactionId = em.merge(transactionId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionRooms transactionRooms) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TransactionRooms persistentTransactionRooms = em.find(TransactionRooms.class, transactionRooms.getId());
            Transactions transactionIdOld = persistentTransactionRooms.getTransactionId();
            Transactions transactionIdNew = transactionRooms.getTransactionId();
            if (transactionIdNew != null) {
                transactionIdNew = em.getReference(transactionIdNew.getClass(), transactionIdNew.getId());
                transactionRooms.setTransactionId(transactionIdNew);
            }
            transactionRooms = em.merge(transactionRooms);
            if (transactionIdOld != null && !transactionIdOld.equals(transactionIdNew)) {
                transactionIdOld.getTransactionRoomsCollection().remove(transactionRooms);
                transactionIdOld = em.merge(transactionIdOld);
            }
            if (transactionIdNew != null && !transactionIdNew.equals(transactionIdOld)) {
                transactionIdNew.getTransactionRoomsCollection().add(transactionRooms);
                transactionIdNew = em.merge(transactionIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = transactionRooms.getId();
                if (findTransactionRooms(id) == null) {
                    throw new NonexistentEntityException("The transactionRooms with id " + id + " no longer exists.");
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
            TransactionRooms transactionRooms;
            try {
                transactionRooms = em.getReference(TransactionRooms.class, id);
                transactionRooms.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionRooms with id " + id + " no longer exists.", enfe);
            }
            Transactions transactionId = transactionRooms.getTransactionId();
            if (transactionId != null) {
                transactionId.getTransactionRoomsCollection().remove(transactionRooms);
                transactionId = em.merge(transactionId);
            }
            em.remove(transactionRooms);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TransactionRooms> findTransactionRoomsEntities() {
        return findTransactionRoomsEntities(true, -1, -1);
    }

    public List<TransactionRooms> findTransactionRoomsEntities(int maxResults, int firstResult) {
        return findTransactionRoomsEntities(false, maxResults, firstResult);
    }

    private List<TransactionRooms> findTransactionRoomsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionRooms.class));
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

    public TransactionRooms findTransactionRooms(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionRooms.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionRoomsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionRooms> rt = cq.from(TransactionRooms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TransactionRooms> findTransactionRoomsById(Integer id) {
        return getEntityManager().createQuery("SELECT tr FROM TransactionRooms tr WHERE tr.transactionId.id = ?1").setParameter(1, id).getResultList();
    }

    
    public List<TransactionRooms> findTransactionRoomsByDate(Date start, Date end) {
        return getEntityManager().createQuery("SELECT tr FROM TransactionRooms tr WHERE tr.arrivalDate >= :start AND tr.departureDate <= :end")
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }
}
