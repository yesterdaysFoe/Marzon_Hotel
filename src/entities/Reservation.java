/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import utils.Initializer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "reservation")
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")})
public class Reservation implements Serializable {
    @Basic(optional = false)
    @Column(name = "down_payment")
    private double downPayment;
    @Column(name = "room_name_number")
    private String roomNameNumber;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservation_id")
    private Integer reservationId;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @Column(name = "zip")
    private int zip;
    @Basic(optional = false)
    @Column(name = "province")
    private String province;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contact")
    private int contact;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "arrival")
    private String arrival;
    @Basic(optional = false)
    @Column(name = "departure")
    private String departure;
    @Basic(optional = false)
    @Column(name = "adults")
    private int adults;
    @Basic(optional = false)
    @Column(name = "child")
    private int child;
    @Basic(optional = false)
    @Column(name = "result")
    private int result;
    @Basic(optional = false)
    @Column(name = "room_id")
    private int roomId;
    @Basic(optional = false)
    @Column(name = "no_room")
    private int noRoom;
    @Basic(optional = false)
    @Column(name = "payable")
    private int payable;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "confirmation")
    private String confirmation;

    public Reservation() {
    }

    public Reservation(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Reservation(Integer reservationId, String firstname, String lastname, String city, int zip, String province, String country, String email, int contact, String username, String password, String arrival, String departure, int adults, int child, int result, int roomId, int noRoom, int payable, String status, String confirmation) {
        this.reservationId = reservationId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.zip = zip;
        this.province = province;
        this.country = country;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = password;
        this.arrival = arrival;
        this.departure = departure;
        this.adults = adults;
        this.child = child;
        this.result = result;
        this.roomId = roomId;
        this.noRoom = noRoom;
        this.payable = payable;
        this.status = status;
        this.confirmation = confirmation;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    public int getPayable() {
        return payable;
    }

    public void setPayable(int payable) {
        this.payable = payable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.remote.Reservation[ reservationId=" + reservationId + " ]";
    }

    public String getFullName() {
        return getLastname() + ", " + getFirstname();
    }

    public long getNumberOfDates() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date arrivalDate = sdf.parse(getArrival());
            Date departureDate = sdf.parse(getDeparture());
            long total = ((departureDate.getTime() - arrivalDate.getTime()) / 86400000) + 1;
            return total;
        } catch (ParseException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public String getRoomName() {
        Room room = Initializer.getRoomJpaController().findRoom(getRoomId());
        return room.getType();
    }

    public int getRoomPrice() {
        Room room = Initializer.getRoomJpaController().findRoom(getRoomId());
        return room.getRate();
    }

    public String getRoomNameNumber() {
        return roomNameNumber;
    }

    public void setRoomNameNumber(String roomNameNumber) {
        this.roomNameNumber = roomNameNumber;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

}
