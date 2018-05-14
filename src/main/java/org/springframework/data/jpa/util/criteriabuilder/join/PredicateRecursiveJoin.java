package org.springframework.data.jpa.util.criteriabuilder.join;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.util.SpecificationSearchCriteria;

/**
 * Create a comparison predicate in recursive strategy to make a complex join
 * filter
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 12/05/2018 23:24:12
 */
public class PredicateRecursiveJoin implements PredicateJoin {

	@Override
	public <E> Predicate getPredicate(From<?, ?> root, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
			String fieldName, String targetField, Object fieldValue) {
		Join<E, ?> join = root.join(fieldName, JoinType.INNER);
		SpecificationSearchCriteria<?, E> spec = new SpecificationSearchCriteria<Object, E>(fieldValue);
		return spec.buildPredicate(join, criteriaQuery, criteriaBuilder);
	}

}
