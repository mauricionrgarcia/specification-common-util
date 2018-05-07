package org.springframework.data.jpa.util.criteriabuilder;

import org.springframework.data.jpa.util.SpecificationField;

/**
 * Types to provide ComparisonPredicate for a {@link SpecificationField}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 06/05/2018 20:44:30
 */
public enum ComparisonPredicateType {

	EQUALS(new ComparisonPredicateEqual()),
	LESS_THAN(new ComparisonPredicateLessThan());

	/**
	 * ComparisonPredicate
	 */
	private ComparisonPredicate comparisonPredicate;

	/**
	 * Contructor:
	 * 
	 * @param comparisonPredicate impl to generate Predicates
	 */
	private ComparisonPredicateType(ComparisonPredicate comparisonPredicate) {
		this.comparisonPredicate = comparisonPredicate;
	}

	/**
	 * @return the comparisonPredicate impl
	 */
	public ComparisonPredicate comparisonPredicate() {
		return comparisonPredicate;
	}

}
