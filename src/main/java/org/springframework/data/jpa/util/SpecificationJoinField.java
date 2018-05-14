package org.springframework.data.jpa.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.jpa.util.criteriabuilder.PredicateJoinType;

/**
 *
 * Annotation responsible for providing additional information to make a join
 * 
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 12/05/2018 20:19:13
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecificationJoinField {

	/**
	 * Class use to make a predicate join.
	 * 
	 * @return PredicateJoinType to make a join Predicate: <strong>default
	 *         {@link PredicateSimpleJoin}</strong>
	 */
	PredicateJoinType joinType() default PredicateJoinType.SIMPLE;

	/**
	 * 
	 * Target field in join
	 * 
	 * @return name of target field to join
	 */
	String targetField() default "";

}