package br.com.microservices.productapi.validators.annotations;

import br.com.microservices.productapi.validators.QuantityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { QuantityValidator.class })
public @interface QuantityConstraint {

    String message() default "{validator.CannotBeLessThanOrEqualZero}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String customMessage() default "";
}
