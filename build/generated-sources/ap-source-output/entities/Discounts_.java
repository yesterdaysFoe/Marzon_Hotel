package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Discounts.class)
public abstract class Discounts_ {

	public static volatile SingularAttribute<Discounts, Double> amount;
	public static volatile SingularAttribute<Discounts, Integer> id;
	public static volatile SingularAttribute<Discounts, String> name;
	public static volatile SingularAttribute<Discounts, Boolean> showOtherInfo;
	public static volatile SingularAttribute<Discounts, Boolean> active;

}

