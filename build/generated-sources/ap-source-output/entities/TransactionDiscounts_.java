package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransactionDiscounts.class)
public abstract class TransactionDiscounts_ {

	public static volatile SingularAttribute<TransactionDiscounts, Double> amount;
	public static volatile SingularAttribute<TransactionDiscounts, Integer> id;
	public static volatile SingularAttribute<TransactionDiscounts, Transactions> transactionId;
	public static volatile SingularAttribute<TransactionDiscounts, String> name;
	public static volatile SingularAttribute<TransactionDiscounts, String> type;
	public static volatile SingularAttribute<TransactionDiscounts, String> comments;

}

