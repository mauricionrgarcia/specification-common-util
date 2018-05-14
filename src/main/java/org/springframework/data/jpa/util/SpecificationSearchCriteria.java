package org.springframework.data.jpa.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.exception.ReadFilterFieldException;
import org.springframework.data.jpa.exception.ValidationFilterFiledException;
import org.springframework.data.jpa.util.criteriabuilder.ComparisonPredicate;
import org.springframework.data.jpa.util.criteriabuilder.ComparisonPredicateEqual;
import org.springframework.data.jpa.util.criteriabuilder.Predicates;
import org.springframework.data.jpa.util.validate.NotBlankFilterField;
import org.springframework.data.jpa.util.validate.ValidateFilterField;
import org.springframework.util.Assert;

/**
 * features to build {@link Specification} from any Filter Pojo.
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 04/05/2018 21:33:51
 * @param <E> Entity
 * @param <F> Filter Java Pojo
 */
public class SpecificationSearchCriteria<F, E> implements Specification<E> {

	/**
	 * Search filter criteria
	 */
	private final F criteria;

	/**
	 * Default ComparisonPredicate to generate the predicates from fields
	 */
	private ComparisonPredicate defaultComparisonPredicate;

	/**
	 * Default Validate Filter Field to validate if the field is valid to research
	 */
	private ValidateFilterField defaultValidateFilterField;

	/**
	 * Default contructor
	 */
	public SpecificationSearchCriteria(final F criteria) {
		this.criteria = criteria;
		this.contructFields();
	}

	/**
	 * contruct filed with defauls values
	 */
	public void contructFields() {
		this.defaultComparisonPredicate = new ComparisonPredicateEqual();
		this.defaultValidateFilterField = new NotBlankFilterField();
	}

	@Override
	public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		return this.buildPredicate(root, criteriaQuery, criteriaBuilder);
	}

	/**
	 * Build a predicate from criteria
	 * 
	 * @param root A root type in the from clause
	 * @param criteriaBuilder constructor criteria queries
	 * @param criteriaQuery query
	 * @return all predicate from the filter.
	 */
	public Predicate buildPredicate(From<?, ?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = this.buildPredicateCondition(root, criteriaQuery, criteriaBuilder);

		if (CollectionUtils.isNotEmpty(predicates)) {
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		}

		return Predicates.noneFilter(criteriaBuilder);

	}

	/**
	 * Build the Predicates from the filter.
	 *
	 * @param root {@link Root}
	 * @param criteriaQuery {@link CriteriaQuery}
	 * @param criteriaBuilder {@link CriteriaBuilder}
	 * @return Predicates from the filter.
	 */
	private List<Predicate> buildPredicateCondition(final From<?, ?> root, final CriteriaQuery<?> criteriaQuery,
			final CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicates = new ArrayList<>();
		Field[] fields = this.criteria.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (!java.lang.reflect.Modifier.isStatic(field.getModifiers()) && isSpecificationField(field)) {

				SpecificationField specificationField = field.getAnnotation(SpecificationField.class);
				Object fieldValue = fieldValue(field);

				if (isValidFilterField(specificationField, fieldValue)) {

					Predicate predicate = null;

					SpecificationJoinField join = field.getAnnotation(SpecificationJoinField.class);
					if (join != null) {
						predicate = makeJoinPredicate(specificationField, criteriaBuilder, fieldValue, root,
								field.getName(), join, criteriaQuery);
					} else {
						predicate = makePredicate(specificationField, criteriaBuilder, fieldValue, root,
								field.getName());
					}
					predicates.add(predicate);
				}
			}
		}
		return predicates;
	}

	private Predicate makeJoinPredicate(SpecificationField specificationField, CriteriaBuilder criteriaBuilder,
			Object fieldValue, From<?, ?> root, String name, SpecificationJoinField join,
			CriteriaQuery<?> criteriaQuery) {
		Assert.notNull(join.joinType(),
				"Null join type is not valid, the field " + name + "don't have a validpredicate");
		return join.joinType().predicateJoin().getPredicate(root, criteriaBuilder, criteriaQuery, name,
				join.targetField(), fieldValue);
	}

	private Predicate makePredicate(SpecificationField specificationField, final CriteriaBuilder criteriaBuilder,
			Object fieldValue, From<?, ?> root, String fieldName) {

		if (specificationField != null) {
			ComparisonPredicate comparisonPredicate = specificationField.comparison().comparisonPredicate();
			return comparisonPredicate.getPredicate(root, criteriaBuilder, fieldName, fieldValue);
		}
		return defaultComparisonPredicate.getPredicate(root, criteriaBuilder, fieldName, fieldValue);
	}

	/**
	 * Validate filter field to build {@link Predicate}
	 * 
	 * @param fieldValue field value
	 * @param specificationField providing additional information to customize the
	 *            build of the Predicate
	 * 
	 * @return true if the field is valid, else false
	 */
	private boolean isValidFilterField(SpecificationField specificationField, Object fieldValue) {

		if (specificationField != null) {
			Class<? extends ValidateFilterField>[] validates = specificationField.validates();
			if (validates != null) {
				for (Class<? extends ValidateFilterField> validateClass : validates) {
					ValidateFilterField validate = getValidateInstance(validateClass);
					if (!validate.isValid(specificationField, fieldValue)) {
						return Boolean.FALSE;
					}
				}
			}
		} else if (defaultValidateFilterField != null) {
			return defaultValidateFilterField.isValid(specificationField, fieldValue);
		}

		return Boolean.TRUE;
	}

	/**
	 * Returns a new instance of the specified.
	 * 
	 * @param cls class to provide a validate filter implementation.
	 * @throws ValidationFilterFiledException if fail to instance a
	 *             {@link ValidateFilterField}.
	 */
	private ValidateFilterField getValidateInstance(final Class<? extends ValidateFilterField> cls) {
		try {
			return ConstructorUtils.invokeConstructor(cls);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException
				| InstantiationException e) {
			throw new ValidationFilterFiledException("Fail to get a intance of ValidateFilterField", e);
		}
	}

	/**
	 * Returns true if the current field don'ts marked as ignored.<br>
	 * 
	 * @param field field attribute to check.
	 * @return true if {@link IgnoreSpecificationField} is not present and the
	 *         property <strong>ignore</strong> is false in
	 *         {@link SpecificationField}, else false.
	 */
	private boolean isSpecificationField(Field field) {

		if (field.isAnnotationPresent(IgnoreSpecificationField.class)) {
			return Boolean.FALSE;
		}

		SpecificationField specificationField = field.getAnnotation(SpecificationField.class);
		return specificationField == null || (specificationField != null && !specificationField.ignore());

	}

	/**
	 * Read value of field.
	 * 
	 * @param field field of filter.
	 * @return value of field.
	 * @throws ReadFilterFieldException if fail in read value of field.
	 */
	private Object fieldValue(Field field) {
		boolean fieldModifier = Modifier.isPublic(field.getModifiers());

		try {
			if (!fieldModifier) {
				field.setAccessible(Boolean.TRUE);
			}
			return field.get(this.criteria);
		} catch (IllegalAccessException | ValidationFilterFiledException e) {
			throw new ReadFilterFieldException(field.getName(), this.criteria, field.getDeclaringClass(), e);
		} finally {
			field.setAccessible(fieldModifier);
		}
	}

	/**
	 * @param defaultComparisonPredicate the defaultComparisonPredicate to set
	 */
	public void setDefaultComparisonPredicate(ComparisonPredicate defaultComparisonPredicate) {
		Assert.notNull(defaultComparisonPredicate, "Comparison Predicate cannot be null");
		this.defaultComparisonPredicate = defaultComparisonPredicate;
	}

	/**
	 * @param defaultValidateFilterField the defaultValidateFilterField to set
	 */
	public void setDefaultValidateFilterField(ValidateFilterField defaultValidateFilterField) {
		Assert.notNull(defaultComparisonPredicate, "Validate Filter Field cannot be null");
		this.defaultValidateFilterField = defaultValidateFilterField;
	}

}
