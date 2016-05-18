package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Roominventory.class)
public abstract class Roominventory_ {

	public static volatile SingularAttribute<Roominventory, Integer> roominventoryId;
	public static volatile SingularAttribute<Roominventory, String> status;
	public static volatile SingularAttribute<Roominventory, Integer> qtyReserve;
	public static volatile SingularAttribute<Roominventory, String> confirmation;
	public static volatile SingularAttribute<Roominventory, Integer> roomId;
	public static volatile SingularAttribute<Roominventory, String> departure;
	public static volatile SingularAttribute<Roominventory, String> arrival;

}

