package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Room.class)
public abstract class Room_ {

	public static volatile SingularAttribute<Room, Integer> maxAdult;
	public static volatile SingularAttribute<Room, Integer> rate;
	public static volatile SingularAttribute<Room, String> description;
	public static volatile SingularAttribute<Room, Integer> maxChild;
	public static volatile SingularAttribute<Room, String> image;
	public static volatile SingularAttribute<Room, Integer> roomId;
	public static volatile SingularAttribute<Room, Integer> qty;
	public static volatile SingularAttribute<Room, String> type;

}

