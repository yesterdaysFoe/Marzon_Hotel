package entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RoomStatus.class)
public abstract class RoomStatus_ {

	public static volatile SingularAttribute<RoomStatus, Integer> id;
	public static volatile SingularAttribute<RoomStatus, Date> startDate;
	public static volatile SingularAttribute<RoomStatus, Integer> status;
	public static volatile SingularAttribute<RoomStatus, Rooms> roomId;

}

