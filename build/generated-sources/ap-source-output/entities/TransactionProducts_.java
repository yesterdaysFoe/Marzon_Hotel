package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransactionProducts.class)
public abstract class TransactionProducts_ {

	public static volatile SingularAttribute<TransactionProducts, Integer> id;
	public static volatile SingularAttribute<TransactionProducts, Transactions> transactionId;
	public static volatile SingularAttribute<TransactionProducts, Double> price;
	public static volatile SingularAttribute<TransactionProducts, ProductsAndServices> productAndServicesId;
	public static volatile SingularAttribute<TransactionProducts, Integer> qty;
	public static volatile SingularAttribute<TransactionProducts, Double> totalPrice;

}

