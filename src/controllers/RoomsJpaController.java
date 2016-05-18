package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.Rooms;
import java.io.Serializable;
import java.util.Collection;
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
public class RoomsJpaController implements Serializable {

    public RoomsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rooms rooms) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rooms);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rooms rooms) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            rooms = em.merge(rooms);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rooms.getId();
                if (findRooms(id) == null) {
                    throw new NonexistentEntityException("The rooms with id " + id + " no longer exists.");
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
            Rooms rooms;
            try {
                rooms = em.getReference(Rooms.class, id);
                rooms.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rooms with id " + id + " no longer exists.", enfe);
            }
            em.remove(rooms);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rooms> findRoomsEntities() {
        return findRoomsEntities(true, -1, -1);
    }

    public List<Rooms> findRoomsEntities(int maxResults, int firstResult) {
        return findRoomsEntities(false, maxResults, firstResult);
    }

    private List<Rooms> findRoomsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rooms.class));
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

    public Rooms findRooms(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rooms.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rooms> rt = cq.from(Rooms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Collection<? extends Rooms> findByRoomTypeId(Integer roomId) {
        return getEntityManager().createQuery("SELECT r FROM Rooms r WHERE r.roomTypeId = ?1").setParameter(1, roomId).getResultList();
    }

}
