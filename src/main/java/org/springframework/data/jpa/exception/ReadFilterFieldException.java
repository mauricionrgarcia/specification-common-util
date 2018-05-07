package org.springframework.data.jpa.exception;

import org.springframework.data.jpa.util.validate.ValidateFilterField;

/**
 * RuntimeException in read value of field in filter
 * 
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio</a>
 * @version
 * @sinse 04/05/2018 23:55:54
 */
public class ReadFilterFieldException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7903511440153607810L;

	/**
	 * Fail in read value of field.
	 * 
	 * @param fieldName field name
	 * @param clazz {@link ValidateFilterField}
	 * @param throwable the cause
	 */
	public ReadFilterFieldException(String fieldName, Object obj, Class<?> clazz, Throwable throwable) {
		super("Fail to read value of field " + fieldName + " in class " + clazz.getSimpleName() + " to: " + obj,
				throwable);
	}

	/**
	 * Default contructor
	 * 
	 * @param message message exception
	 * @param throwable the cause
	 */
	public ReadFilterFieldException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
