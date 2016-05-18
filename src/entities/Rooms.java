package entities;

import connection.DbConf;
import controllers.RoomJpaController;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "rooms")
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r")})
public class Rooms implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private Collection<RoomStatus> roomStatusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Collection<TransactionRooms> transactionRoomsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "room_type_id")
    private int roomTypeId;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    public Rooms() {
    }

    public Rooms(Integer id) {
        this.id = id;
    }

    public Rooms(Integer id, String name, int roomTypeId, String description) {
        this.id = id;
        this.name = name;
        this.roomTypeId = roomTypeId;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getRoomTypeName() {
        RoomJpaController rjc = new RoomJpaController(DbConf.getInstance());
        return rjc.findRoom(getRoomTypeId()).getType();
    }

    public Collection<TransactionRooms> getTransactionRoomsCollection() {
        return transactionRoomsCollection;
    }

    public void setTransactionRoomsCollection(Collection<TransactionRooms> transactionRoomsCollection) {
        this.transactionRoomsCollection = transactionRoomsCollection;
    }

    public Collection<RoomStatus> getRoomStatusCollection() {
        return roomStatusCollection;
    }

    public void setRoomStatusCollection(Collection<RoomStatus> roomStatusCollection) {
        this.roomStatusCollection = roomStatusCollection;
    }
    
    

}
