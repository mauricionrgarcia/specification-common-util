package org.springframework.data.jpa.util.criteriabuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Create predicate with a comparison predicate
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 06/05/2018 20:38:34
 */
public interface ComparisonPredicate {

	/**
	 * Generate a predicate
	 * 
	 * @param root A root type in the from clause
	 * @param criteriaBuilder constructor criteria queries
	 * @param fieldName name of field
	 * @param fieldValue value of field
	 * @return simple predicate
	 */
	public Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, Object fieldValue);

}
