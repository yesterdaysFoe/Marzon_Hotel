package entities;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductsAndServices.class)
public abstract class ProductsAndServices_ {

	public static volatile SingularAttribute<ProductsAndServices, Integer> id;
	public static volatile CollectionAttribute<ProductsAndServices, TransactionProducts> transactionProductsCollection;
	public static volatile SingularAttribute<ProductsAndServices, String> category;
	public static volatile SingularAttribute<ProductsAndServices, String> foodCategory;
	public static volatile SingularAttribute<ProductsAndServices, Double> price;
	public static volatile SingularAttribute<ProductsAndServices, String> details;
	public static volatile SingularAttribute<ProductsAndServices, String> name;
	public static volatile SingularAttribute<ProductsAndServices, String> foodType;

}

