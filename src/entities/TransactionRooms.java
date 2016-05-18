package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import utils.Initializer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "transaction_rooms")
@NamedQueries({
    @NamedQuery(name = "TransactionRooms.findAll", query = "SELECT t FROM TransactionRooms t")})
public class TransactionRooms implements Serializable {
    @JoinColumn(name = "room", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rooms room;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "room_id")
    private int roomId;
    @Basic(optional = false)
    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @Basic(optional = false)
    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @ManyToOne
    private Transactions transactionId;

    public TransactionRooms() {
    }

    public TransactionRooms(Integer id) {
        this.id = id;
    }

    public TransactionRooms(Integer id, int roomId, Date arrivalDate, Date departureDate, double price, int qty) {
        this.id = id;
        this.roomId = roomId;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        if (!(object instanceof TransactionRooms)) {
            return false;
        }
        TransactionRooms other = (TransactionRooms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.local.TransactionRooms[ id=" + id + " ]";
    }

    public Transactions getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transactions transactionId) {
        this.transactionId = transactionId;
    }

    public long getNumberOfDates() {
        long total = ((getDepartureDate().getTime() - getArrivalDate().getTime()) / 86400000) + 1;
        return total;
    }

    public double getTotalPrice() {
        return (double) qty * price;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }
    
    public String getDateArrivalStr(){
       return Initializer.sdf.format(getArrivalDate());
    }
    
    public String getDateDepartureStr(){
        return Initializer.sdf.format(getDepartureDate());
    }
}
