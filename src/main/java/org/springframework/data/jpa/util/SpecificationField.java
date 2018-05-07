package org.springframework.data.jpa.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.util.criteriabuilder.ComparisonPredicateEqual;
import org.springframework.data.jpa.util.criteriabuilder.ComparisonPredicateType;
import org.springframework.data.jpa.util.validate.NotBlankFilterField;
import org.springframework.data.jpa.util.validate.ValidateFilterField;

/**
 *
 * Annotation responsible for providing additional information to customize the
 * build of the Predicate in {@link SpecificationSearchCriteria}
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 04/05/2018 22:21:58
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecificationField {

	/**
	 * List class validation {@link ValidateFilterField} to check if the field is
	 * valid to include in {@link Predicate}.
	 * 
	 * @return List class validation {@link ValidateFilterField}: <strong>default {
	 *         {@link NotBlankFilterField}}</strong>
	 */
	Class<? extends ValidateFilterField>[] validates() default { NotBlankFilterField.class };

	/**
	 * Class use to make a predicate.
	 * 
	 * @return ComparisonPredicate to make a Predicate: <strong>default
	 *         {@link ComparisonPredicateEqual}</strong>
	 */
	ComparisonPredicateType comparison() default ComparisonPredicateType.EQUALS;

	/**
	 * true if the current field is marked to be ignore in specification criteria.
	 * <p>
	 * 
	 * Even marked as not to ignore, the annotation {@link IgnoreSpecificationField}
	 * overlaps
	 * 
	 * @return if the current field is marked to be ignore: <strong>default is
	 *         false</strong>
	 */
	boolean ignore() default false;

}