package entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransactionPayments.class)
public abstract class TransactionPayments_ {

	public static volatile SingularAttribute<TransactionPayments, Double> amount;
	public static volatile SingularAttribute<TransactionPayments, Integer> id;
	public static volatile SingularAttribute<TransactionPayments, Transactions> transactionId;
	public static volatile SingularAttribute<TransactionPayments, String> name;
	public static volatile SingularAttribute<TransactionPayments, Date> dateOfPayment;

}

