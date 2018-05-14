package org.springframework.data.jpa.util.criteriabuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 * Create a comparison predicate with condition less than
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 06/05/2018 20:39:42
 */
public class ComparisonPredicateLessThan implements ComparisonPredicate {

	@Override
	public Predicate getPredicate(From<?, ?> root, CriteriaBuilder criteriaBuilder, String fieldName,
			Object fieldValue) {
		return lessThan(root, criteriaBuilder, fieldName, fieldValue);
	}

	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> Predicate lessThan(From<?, ?> root, CriteriaBuilder criteriaBuilder,
			String fieldName, Object fieldValue) {
		Path<T> path = root.get(fieldName);
		return criteriaBuilder.lessThan(path, (T) fieldValue);
	}

}
