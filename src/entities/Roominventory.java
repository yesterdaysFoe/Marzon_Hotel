/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "roominventory")
@NamedQueries({
    @NamedQuery(name = "Roominventory.findAll", query = "SELECT r FROM Roominventory r")})
public class Roominventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roominventory_id")
    private Integer roominventoryId;
    @Basic(optional = false)
    @Column(name = "arrival")
    private String arrival;
    @Basic(optional = false)
    @Column(name = "departure")
    private String departure;
    @Basic(optional = false)
    @Column(name = "qty_reserve")
    private int qtyReserve;
    @Basic(optional = false)
    @Column(name = "room_id")
    private int roomId;
    @Basic(optional = false)
    @Column(name = "confirmation")
    private String confirmation;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public Roominventory() {
    }

    public Roominventory(Integer roominventoryId) {
        this.roominventoryId = roominventoryId;
    }

    public Roominventory(Integer roominventoryId, String arrival, String departure, int qtyReserve, int roomId, String confirmation, String status) {
        this.roominventoryId = roominventoryId;
        this.arrival = arrival;
        this.departure = departure;
        this.qtyReserve = qtyReserve;
        this.roomId = roomId;
        this.confirmation = confirmation;
        this.status = status;
    }

    public Integer getRoominventoryId() {
        return roominventoryId;
    }

    public void setRoominventoryId(Integer roominventoryId) {
        this.roominventoryId = roominventoryId;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public int getQtyReserve() {
        return qtyReserve;
    }

    public void setQtyReserve(int qtyReserve) {
        this.qtyReserve = qtyReserve;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roominventoryId != null ? roominventoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roominventory)) {
            return false;
        }
        Roominventory other = (Roominventory) object;
        if ((this.roominventoryId == null && other.roominventoryId != null) || (this.roominventoryId != null && !this.roominventoryId.equals(other.roominventoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.remote.Roominventory[ roominventoryId=" + roominventoryId + " ]";
    }
    
}
