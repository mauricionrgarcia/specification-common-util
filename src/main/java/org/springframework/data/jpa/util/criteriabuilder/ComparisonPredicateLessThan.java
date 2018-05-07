package org.springframework.data.jpa.util.criteriabuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Create a comparison predicate with condition less than
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 06/05/2018 20:39:42
 */
public class ComparisonPredicateLessThan implements ComparisonPredicate {

	@Override
	public Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, String fieldName, Object fieldValue) {
		return lessThan(root, criteriaBuilder, fieldName, fieldValue);
	}

	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> Predicate lessThan(Root<?> root, CriteriaBuilder criteriaBuilder,
			String fieldName, Object fieldValue) {
		Path<T> path = root.get(fieldName);
		return criteriaBuilder.lessThan(path, (T) fieldValue);
	}

}
