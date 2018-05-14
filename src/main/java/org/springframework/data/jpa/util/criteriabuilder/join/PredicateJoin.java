package org.springframework.data.jpa.util.criteriabuilder.join;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;

/**
 * Create predicate with a comparison predicate using join
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 12/05/2018 23:01:55
 */
public interface PredicateJoin {

	/**
	 * Generate a predicate
	 * 
	 * @param root A root type in the from clause
	 * @param criteriaBuilder constructor criteria queries
	 * @param criteriaQuery query
	 * @param fieldName name of field from mapping
	 * @param targetField name of target join
	 * @param fieldValue value of field
	 * @return join predicate
	 */
	<E> Predicate getPredicate(From<?, ?> root, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
			String fieldName, String targetField, Object fieldValue);

}
