package org.springframework.data.jpa.util.criteriabuilder.join;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.util.Assert;

/**
 * Create a comparison predicate equals join equals
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 12/05/2018 22:51:20
 */
public class PredicateSimpleJoin implements PredicateJoin {

	@Override
	public <E> Predicate getPredicate(From<?, ?> root, CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery,
			String fieldName, String targetField, Object fieldValue) {
		Assert.hasText(targetField,
				"TargetField is requided when use PredicateJoinType.SIMPLE, see field: " + fieldName);
		Join<?, ?> join = root.join(fieldName, JoinType.INNER);
		return criteriaBuilder.equal(join.get(targetField), fieldValue);
	}
}
