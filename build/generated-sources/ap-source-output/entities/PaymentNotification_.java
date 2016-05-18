package entities;

import java.math.BigInteger;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PaymentNotification.class)
public abstract class PaymentNotification_ {

	public static volatile SingularAttribute<PaymentNotification, BigInteger> amount;
	public static volatile SingularAttribute<PaymentNotification, Integer> payId;
	public static volatile SingularAttribute<PaymentNotification, String> itemName;
	public static volatile SingularAttribute<PaymentNotification, String> status;
	public static volatile SingularAttribute<PaymentNotification, String> itemNumber;
	public static volatile SingularAttribute<PaymentNotification, String> txnId;
	public static volatile SingularAttribute<PaymentNotification, String> payerEmail;
	public static volatile SingularAttribute<PaymentNotification, String> currency;

}

