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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import utils.Initializer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "transaction_products")
@NamedQueries({
    @NamedQuery(name = "TransactionProducts.findAll", query = "SELECT t FROM TransactionProducts t")})
public class TransactionProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "total_price")
    private double totalPrice;
    @JoinColumn(name = "product_and_services_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductsAndServices productAndServicesId;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @ManyToOne
    private Transactions transactionId;

    public TransactionProducts() {
    }

    public TransactionProducts(Integer id) {
        this.id = id;
    }

    public TransactionProducts(Integer id, int qty, double price, double totalPrice) {
        this.id = id;
        this.qty = qty;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProductsAndServices getProductAndServicesId() {
        return productAndServicesId;
    }

    public void setProductAndServicesId(ProductsAndServices productAndServicesId) {
        this.productAndServicesId = productAndServicesId;
    }

    public Transactions getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transactions transactionId) {
        this.transactionId = transactionId;
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
        if (!(object instanceof TransactionProducts)) {
            return false;
        }
        TransactionProducts other = (TransactionProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.local.TransactionProducts[ id=" + id + " ]";
    }

    public String getPriceStr() {
        return Initializer.df.format(price);
    }

    public String getTotalPriceStr() {
        return Initializer.df.format(getTotalPrice());
    }
}
