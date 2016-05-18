package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> position;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Integer> userId;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, String> password;

}

