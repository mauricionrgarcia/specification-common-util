package org.springframework.data.jpa.util.validate;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.jpa.util.SpecificationField;
import org.springframework.data.jpa.util.SpecificationSearchCriteria;

/**
 * 
 * Validate filter field to build {@link Predicate} @see
 * {@link SpecificationSearchCriteria}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 04/05/2018 22:52:40
 */
public class NotBlankFilterField implements ValidateFilterField {

	public boolean isValid(SpecificationField specificationField, Object fieldValue) {
		return BooleanUtils.isFalse(org.springframework.util.ObjectUtils.isEmpty(fieldValue));
	}
}
