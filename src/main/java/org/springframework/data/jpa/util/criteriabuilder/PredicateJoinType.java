package org.springframework.data.jpa.util.criteriabuilder;

import org.springframework.data.jpa.util.SpecificationJoinField;
import org.springframework.data.jpa.util.criteriabuilder.join.PredicateJoin;

import org.springframework.data.jpa.util.criteriabuilder.join.PredicateSimpleJoin;
import org.springframework.data.jpa.util.criteriabuilder.join.PredicateRecursiveJoin;

/**
 * Types to provide PredicateJoin for a {@link SpecificationJoinField}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 13/05/2018 20:24:17
 */
public enum PredicateJoinType {

	SIMPLE(new PredicateSimpleJoin()), RECURSIVE(new PredicateRecursiveJoin());

	/**
	 * ComparisonPredicate
	 */
	private PredicateJoin predicateJoin;

	/**
	 * Contructor:
	 * 
	 * @param PredicateJoin impl to generate joins Predicates
	 */
	private PredicateJoinType(PredicateJoin predicateJoin) {
		this.predicateJoin = predicateJoin;
	}

	/**
	 * @return the comparisonPredicate impl
	 */
	public PredicateJoin predicateJoin() {
		return predicateJoin;
	}

}
