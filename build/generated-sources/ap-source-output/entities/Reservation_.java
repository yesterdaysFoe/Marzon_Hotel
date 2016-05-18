package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Reservation.class)
public abstract class Reservation_ {

	public static volatile SingularAttribute<Reservation, Integer> result;
	public static volatile SingularAttribute<Reservation, Integer> zip;
	public static volatile SingularAttribute<Reservation, Integer> payable;
	public static volatile SingularAttribute<Reservation, String> status;
	public static volatile SingularAttribute<Reservation, String> roomNameNumber;
	public static volatile SingularAttribute<Reservation, Integer> noRoom;
	public static volatile SingularAttribute<Reservation, Integer> roomId;
	public static volatile SingularAttribute<Reservation, String> departure;
	public static volatile SingularAttribute<Reservation, Double> downPayment;
	public static volatile SingularAttribute<Reservation, Integer> reservationId;
	public static volatile SingularAttribute<Reservation, String> lastname;
	public static volatile SingularAttribute<Reservation, String> firstname;
	public static volatile SingularAttribute<Reservation, String> password;
	public static volatile SingularAttribute<Reservation, Integer> contact;
	public static volatile SingularAttribute<Reservation, String> city;
	public static volatile SingularAttribute<Reservation, String> country;
	public static volatile SingularAttribute<Reservation, Integer> child;
	public static volatile SingularAttribute<Reservation, String> username;
	public static volatile SingularAttribute<Reservation, String> email;
	public static volatile SingularAttribute<Reservation, String> confirmation;
	public static volatile SingularAttribute<Reservation, Integer> adults;
	public static volatile SingularAttribute<Reservation, String> province;
	public static volatile SingularAttribute<Reservation, String> arrival;

}

