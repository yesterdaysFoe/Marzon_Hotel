package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import utils.Initializer;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
@Entity
@Table(name = "transactions")
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t")})
public class Transactions implements Serializable {
    @Column(name = "reservation_id")
    private String reservationId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_hotel_bill")
    private Double totalHotelBill;
    @Column(name = "total_restaurant_bill")
    private Double totalRestaurantBill;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<TransactionDiscounts> transactionDiscountsCollection;
    @Basic(optional = false)
    @Column(name = "checked_out")
    private boolean checkedOut;
    @Basic(optional = true)
    @Column(name = "discount_name")
    private String discountName;
    @Basic(optional = true)
    @Column(name = "discount_amount")
    private double discountAmount;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "custosmer_address")
    private String custosmerAddress;
    @Column(name = "customer_tel_no")
    private String customerTelNo;
    @Basic(optional = false)
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "zip")
    private String zip;
    @Column(name = "customer_last_name")
    private String customerLastName;
    @Column(name = "customer_first_name")
    private String customerFirstName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<TransactionRooms> transactionRoomsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<TransactionProducts> transactionProductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<TransactionPayments> transactionPaymentsCollection;

    public Transactions() {
    }

    public Transactions(Integer id) {
        this.id = id;
    }

    public Transactions(Integer id, double totalPrice, String customerLastName, String customerFirstName) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.customerLastName = customerLastName;
        this.customerFirstName = customerFirstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustosmerAddress() {
        return custosmerAddress;
    }

    public void setCustosmerAddress(String custosmerAddress) {
        this.custosmerAddress = custosmerAddress;
    }

    public String getCustomerTelNo() {
        return customerTelNo;
    }

    public void setCustomerTelNo(String customerTelNo) {
        this.customerTelNo = customerTelNo;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
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
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.local.Transactions[ id=" + id + " ]";
    }

    public Collection<TransactionProducts> getTransactionProductsCollection() {
        return transactionProductsCollection;
    }

    public void setTransactionProductsCollection(Collection<TransactionProducts> transactionProductsCollection) {
        this.transactionProductsCollection = transactionProductsCollection;
    }

    public Collection<TransactionPayments> getTransactionPaymentsCollection() {
        return transactionPaymentsCollection;
    }

    public void setTransactionPaymentsCollection(Collection<TransactionPayments> transactionPaymentsCollection) {
        this.transactionPaymentsCollection = transactionPaymentsCollection;
    }

    public Collection<TransactionRooms> getTransactionRoomsCollection() {
        return transactionRoomsCollection;
    }

    public void setTransactionRoomsCollection(Collection<TransactionRooms> transactionRoomsCollection) {
        this.transactionRoomsCollection = transactionRoomsCollection;
    }

    public String getName() {
        return customerFirstName + " " + customerLastName;
    }

    public String getAddress() {
        return custosmerAddress + " " + zip;
    }

    public String getTotalPriceStr() {
        return Initializer.df.format(totalPrice);
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public Collection<TransactionDiscounts> getTransactionDiscountsCollection() {
        return transactionDiscountsCollection;
    }

    public void setTransactionDiscountsCollection(Collection<TransactionDiscounts> transactionDiscountsCollection) {
        this.transactionDiscountsCollection = transactionDiscountsCollection;
    }

    public Double getTotalHotelBill() {
        return totalHotelBill;
    }

    public void setTotalHotelBill(Double totalHotelBill) {
        this.totalHotelBill = totalHotelBill;
    }

    public Double getTotalRestaurantBill() {
        return totalRestaurantBill;
    }

    public void setTotalRestaurantBill(Double totalRestaurantBill) {
        this.totalRestaurantBill = totalRestaurantBill;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

}
