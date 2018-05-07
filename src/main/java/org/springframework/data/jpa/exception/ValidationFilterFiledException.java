package org.springframework.data.jpa.exception;

import org.springframework.data.jpa.util.validate.ValidateFilterField;

/**
 * RuntimeException to process to invoke {@link ValidateFilterField}
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 04/05/2018 23:35:39
 */
public class ValidationFilterFiledException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7903511440153607810L;

	/**
	 * Fail in invoke validate.
	 * 
	 * @param fieldValue value of attribute
	 * @param fieldName field name
	 * @param clazz {@link ValidateFilterField}
	 * @param throwable the cause
	 */
	public ValidationFilterFiledException(String fieldValue, String fieldName,
			Class<? extends ValidateFilterField> clazz, Throwable throwable) {
		super("Fail to invoke " + clazz.getSimpleName() + " validation to field " + fieldName + " to attribute "
				+ fieldValue, throwable);
	}

	/**
	 * Default contructor
	 * 
	 * @param message message exception
	 * @param throwable the cause
	 */
	public ValidationFilterFiledException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
