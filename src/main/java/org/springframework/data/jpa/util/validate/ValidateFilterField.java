package org.springframework.data.jpa.util.validate;

import org.springframework.data.jpa.util.SpecificationField;

/**
 * 
 * Validate filter field to build {@link Predicate} @see
 * {@link SpecificationSearchCriteria}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 04/05/2018 22:52:40
 */
public interface ValidateFilterField {

	/**
	 * true if the field is valid.
	 * 
	 * @param specificationField {@link SpecificationField} additional information
	 *            to customize the build of the Predicate
	 * @param fieldValue value of field attribute
	 * @return true if the field is valid, else false
	 */
	boolean isValid(SpecificationField specificationField, Object fieldValue);

}
