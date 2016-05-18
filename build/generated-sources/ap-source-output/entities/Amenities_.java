package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Amenities.class)
public abstract class Amenities_ {

	public static volatile SingularAttribute<Amenities, String> des;
	public static volatile SingularAttribute<Amenities, Integer> amenitiesId;
	public static volatile SingularAttribute<Amenities, String> pic;

}

