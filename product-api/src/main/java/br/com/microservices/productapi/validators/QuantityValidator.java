package br.com.microservices.productapi.validators;

import br.com.microservices.productapi.utils.BigDecimalUtil;
import br.com.microservices.productapi.validators.annotations.QuantityConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class QuantityValidator implements ConstraintValidator<QuantityConstraint, Object> {

    @Override
    public void initialize(QuantityConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true;

//        if (value instanceof BigDecimal)
//            return !BigDecimalUtil.equalOrLessThanZero((BigDecimal) value);

        return true;
    }
}
