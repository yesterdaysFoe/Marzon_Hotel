package controllers;

import controllers.exceptions.NonexistentEntityException;
import entities.RoomStatus;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Rooms;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.Initializer;
import utils.Status;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class RoomStatusJpaController implements Serializable {

    public RoomStatusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RoomStatus roomStatus) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rooms roomId = roomStatus.getRoomId();
            if (roomId != null) {
                roomId = em.getReference(roomId.getClass(), roomId.getId());
                roomStatus.setRoomId(roomId);
            }
            em.persist(roomStatus);
            if (roomId != null) {
                roomId.getRoomStatusCollection().add(roomStatus);
                roomId = em.merge(roomId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RoomStatus roomStatus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RoomStatus persistentRoomStatus = em.find(RoomStatus.class, roomStatus.getId());
            Rooms roomIdOld = persistentRoomStatus.getRoomId();
            Rooms roomIdNew = roomStatus.getRoomId();
            if (roomIdNew != null) {
                roomIdNew = em.getReference(roomIdNew.getClass(), roomIdNew.getId());
                roomStatus.setRoomId(roomIdNew);
            }
            roomStatus = em.merge(roomStatus);
            if (roomIdOld != null && !roomIdOld.equals(roomIdNew)) {
                roomIdOld.getRoomStatusCollection().remove(roomStatus);
                roomIdOld = em.merge(roomIdOld);
            }
            if (roomIdNew != null && !roomIdNew.equals(roomIdOld)) {
                roomIdNew.getRoomStatusCollection().add(roomStatus);
                roomIdNew = em.merge(roomIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roomStatus.getId();
                if (findRoomStatus(id) == null) {
                    throw new NonexistentEntityException("The roomStatus with id " + id + " no longer exists.");
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
            RoomStatus roomStatus;
            try {
                roomStatus = em.getReference(RoomStatus.class, id);
                roomStatus.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roomStatus with id " + id + " no longer exists.", enfe);
            }
//            Rooms roomId = roomStatus.getRoomId();
//            if (roomId != null) {
//                roomId.getRoomStatusCollection().remove(roomStatus);
////                roomId = em.merge(roomId);
//            }
            em.remove(roomStatus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RoomStatus> findRoomStatusEntities() {
        return findRoomStatusEntities(true, -1, -1);
    }

    public List<RoomStatus> findRoomStatusEntities(int maxResults, int firstResult) {
        return findRoomStatusEntities(false, maxResults, firstResult);
    }

    private List<RoomStatus> findRoomStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RoomStatus.class));
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

    public RoomStatus findRoomStatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RoomStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomStatusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RoomStatus> rt = cq.from(RoomStatus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<RoomStatus> findByMonth(Date time) {
        return getEntityManager().createQuery("SELECT rs FROM RoomStatus rs WHERE rs.startDate >= ?1").setParameter(1, time).getResultList();
    }

    public boolean roomIsNotAvailable(Rooms room, Date start, Date end) {
        try {
            return getEntityManager().createQuery("SELECT rs FROM RoomStatus rs WHERE rs.status != :status AND rs.roomId.id = ?2 AND rs.startDate BETWEEN ?1 AND ?3")
                    .setParameter(1, start)
                    .setParameter(3, end)
                    .setParameter("status", Status.RESRVE)
                    .setParameter(2, room.getId())
                    .setMaxResults(1)
                    .getSingleResult() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public int countNoRoomsByDateAndStatus(Date date, int status) {
        try {
            return getEntityManager().createQuery("SELECT rs FROM RoomStatus rs WHERE rs.startDate = ?1 AND rs.status = ?2 GROUP BY rs.roomId")
                    .setParameter(1, date)
                    .setParameter(2, status)
                    .getResultList().size();

        } catch (Exception e) {
            Logger.getLogger(RoomStatusJpaController.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public int countNotAvailableRooms(Date date) {
        try {
            return getEntityManager().createQuery("SELECT rs FROM RoomStatus rs WHERE rs.startDate = ?1 GROUP BY rs.roomId")
                    .setParameter(1, date)
                    .getResultList().size();

        } catch (Exception e) {
            Logger.getLogger(RoomStatusJpaController.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public List<RoomStatus> findRoom(Rooms room, Date start, Date end) {
        return getEntityManager().createQuery("SELECT rs FROM RoomStatus rs WHERE rs.roomId.id = ?2 AND rs.startDate BETWEEN ?1 AND ?3")
                .setParameter(1, start)
                .setParameter(3, end)
                .setParameter(2, room.getId())
                .getResultList();
    }

}
