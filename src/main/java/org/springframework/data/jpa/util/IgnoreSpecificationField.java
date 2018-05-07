package org.springframework.data.jpa.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Annotation responsible for identifying the attributes to dont use in
 * specification.
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version 1
 * @sinse 04/05/2018 21:43:55
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSpecificationField {

}