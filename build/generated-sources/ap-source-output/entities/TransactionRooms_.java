package entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransactionRooms.class)
public abstract class TransactionRooms_ {

	public static volatile SingularAttribute<TransactionRooms, Integer> id;
	public static volatile SingularAttribute<TransactionRooms, Transactions> transactionId;
	public static volatile SingularAttribute<TransactionRooms, Double> price;
	public static volatile SingularAttribute<TransactionRooms, Date> departureDate;
	public static volatile SingularAttribute<TransactionRooms, Integer> roomId;
	public static volatile SingularAttribute<TransactionRooms, Integer> qty;
	public static volatile SingularAttribute<TransactionRooms, Date> arrivalDate;
	public static volatile SingularAttribute<TransactionRooms, Rooms> room;

}

