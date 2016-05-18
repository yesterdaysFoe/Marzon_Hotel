package entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, String> content;
	public static volatile SingularAttribute<Comment, String> email;
	public static volatile SingularAttribute<Comment, String> name;
	public static volatile SingularAttribute<Comment, Integer> commentId;

}

