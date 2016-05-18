/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import utils.Initializer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "products_and_services")
@NamedQueries({
    @NamedQuery(name = "ProductsAndServices.findAll", query = "SELECT p FROM ProductsAndServices p")})
public class ProductsAndServices implements Serializable {
    @Basic(optional = true)
    @Column(name = "food_category")
    private String foodCategory;
    @Basic(optional = true)
    @Column(name = "food_type")
    private String foodType;

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
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @Column(name = "details")
    private String details;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productAndServicesId", fetch = FetchType.EAGER)
    private Collection<TransactionProducts> transactionProductsCollection;

    public ProductsAndServices() {
    }

    public ProductsAndServices(Integer id) {
        this.id = id;
    }

    public ProductsAndServices(Integer id, String name, String category, String details, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.details = details;
        this.price = price;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Collection<TransactionProducts> getTransactionProductsCollection() {
        return transactionProductsCollection;
    }

    public void setTransactionProductsCollection(Collection<TransactionProducts> transactionProductsCollection) {
        this.transactionProductsCollection = transactionProductsCollection;
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
        if (!(object instanceof ProductsAndServices)) {
            return false;
        }
        ProductsAndServices other = (ProductsAndServices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.local.ProductsAndServices[ id=" + id + " ]";
    }

    public String getPriceString() {
        return Initializer.df.format(price);
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

}
