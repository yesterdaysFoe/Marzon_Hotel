package entities;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Rooms.class)
public abstract class Rooms_ {

	public static volatile SingularAttribute<Rooms, Integer> id;
	public static volatile CollectionAttribute<Rooms, TransactionRooms> transactionRoomsCollection;
	public static volatile SingularAttribute<Rooms, String> description;
	public static volatile SingularAttribute<Rooms, String> name;
	public static volatile SingularAttribute<Rooms, Integer> roomTypeId;
	public static volatile CollectionAttribute<Rooms, RoomStatus> roomStatusCollection;

}

