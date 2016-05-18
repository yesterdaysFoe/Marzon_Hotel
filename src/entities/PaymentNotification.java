/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "payment_notification")
@NamedQueries({
    @NamedQuery(name = "PaymentNotification.findAll", query = "SELECT p FROM PaymentNotification p")})
public class PaymentNotification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pay_id")
    private Integer payId;
    @Basic(optional = false)
    @Column(name = "item_name")
    private String itemName;
    @Basic(optional = false)
    @Column(name = "item_number")
    private String itemNumber;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "amount")
    private BigInteger amount;
    @Basic(optional = false)
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @Column(name = "txn_id")
    private String txnId;
    @Basic(optional = false)
    @Column(name = "payer_email")
    private String payerEmail;

    public PaymentNotification() {
    }

    public PaymentNotification(Integer payId) {
        this.payId = payId;
    }

    public PaymentNotification(Integer payId, String itemName, String itemNumber, String status, BigInteger amount, String currency, String txnId, String payerEmail) {
        this.payId = payId;
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.status = status;
        this.amount = amount;
        this.currency = currency;
        this.txnId = txnId;
        this.payerEmail = payerEmail;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payId != null ? payId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentNotification)) {
            return false;
        }
        PaymentNotification other = (PaymentNotification) object;
        if ((this.payId == null && other.payId != null) || (this.payId != null && !this.payId.equals(other.payId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.remote.PaymentNotification[ payId=" + payId + " ]";
    }
    
}
