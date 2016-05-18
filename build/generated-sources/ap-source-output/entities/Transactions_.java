package entities;

import java.util.Date;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Transactions.class)
public abstract class Transactions_ {

	public static volatile SingularAttribute<Transactions, String> zip;
	public static volatile SingularAttribute<Transactions, String> discountName;
	public static volatile SingularAttribute<Transactions, String> reservationId;
	public static volatile SingularAttribute<Transactions, String> customerEmail;
	public static volatile SingularAttribute<Transactions, Date> date;
	public static volatile SingularAttribute<Transactions, Double> discountAmount;
	public static volatile CollectionAttribute<Transactions, TransactionDiscounts> transactionDiscountsCollection;
	public static volatile SingularAttribute<Transactions, String> custosmerAddress;
	public static volatile SingularAttribute<Transactions, String> customerLastName;
	public static volatile SingularAttribute<Transactions, Integer> id;
	public static volatile CollectionAttribute<Transactions, TransactionProducts> transactionProductsCollection;
	public static volatile CollectionAttribute<Transactions, TransactionRooms> transactionRoomsCollection;
	public static volatile CollectionAttribute<Transactions, TransactionPayments> transactionPaymentsCollection;
	public static volatile SingularAttribute<Transactions, Double> totalHotelBill;
	public static volatile SingularAttribute<Transactions, String> customerFirstName;
	public static volatile SingularAttribute<Transactions, Double> totalRestaurantBill;
	public static volatile SingularAttribute<Transactions, Boolean> checkedOut;
	public static volatile SingularAttribute<Transactions, Double> totalPrice;
	public static volatile SingularAttribute<Transactions, String> customerTelNo;

}

