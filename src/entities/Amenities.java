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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "amenities")
@NamedQueries({
    @NamedQuery(name = "Amenities.findAll", query = "SELECT a FROM Amenities a")})
public class Amenities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "amenities_id")
    private Integer amenitiesId;
    @Basic(optional = false)
    @Column(name = "pic")
    private String pic;
    @Basic(optional = false)
    @Lob
    @Column(name = "des")
    private String des;

    public Amenities() {
    }

    public Amenities(Integer amenitiesId) {
        this.amenitiesId = amenitiesId;
    }

    public Amenities(Integer amenitiesId, String pic, String des) {
        this.amenitiesId = amenitiesId;
        this.pic = pic;
        this.des = des;
    }

    public Integer getAmenitiesId() {
        return amenitiesId;
    }

    public void setAmenitiesId(Integer amenitiesId) {
        this.amenitiesId = amenitiesId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amenitiesId != null ? amenitiesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amenities)) {
            return false;
        }
        Amenities other = (Amenities) object;
        if ((this.amenitiesId == null && other.amenitiesId != null) || (this.amenitiesId != null && !this.amenitiesId.equals(other.amenitiesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.remote.Amenities[ amenitiesId=" + amenitiesId + " ]";
    }
    
}
