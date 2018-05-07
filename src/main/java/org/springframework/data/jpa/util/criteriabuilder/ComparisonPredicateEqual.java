package org.springframework.data.jpa.util.criteriabuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Create a comparison predicate equals
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 06/05/2018 20:39:25
 */
public class ComparisonPredicateEqual implements ComparisonPredicate {

	@Override
	public Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, Object fieldValue) {
		return criteriaBuilder.equal(root.get(fieldName), fieldValue);
	}

}
