package org.springframework.data.jpa.util.criteriabuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * Create a comparison predicate equals
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 06/05/2018 20:39:25
 */
public abstract class Predicates {

	/**
	 * Create a conjunction (with zero conjuncts). A conjunction with zero conjuncts
	 * is true.
	 * 
	 * @param criteriaBuilder constructor criteria queries
	 * @return Predicate allways true
	 */
	public static Predicate noneFilter(CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.conjunction();
	}

}
