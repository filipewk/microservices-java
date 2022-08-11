package br.com.microservices.productapi.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class BigDecimalUtil {

    public BigDecimal zeroIfNull(BigDecimal value) {
        if (value == null)
            return BigDecimal.ZERO;
        return value;
    }

    public boolean lessThan(BigDecimal value, BigDecimal value2) {
        return zeroIfNull(value).compareTo(zeroIfNull(value2)) < 0;
    }

    public boolean biggerThan(BigDecimal value, BigDecimal value2) {
        return zeroIfNull(value).compareTo(zeroIfNull(value2)) > 0;
    }

    public boolean equalOrBiggerThan(BigDecimal value, BigDecimal value2) {
        return zeroIfNull(value).compareTo(zeroIfNull(value2)) >= 0;
    }

    public boolean equalOrLessThan(BigDecimal value, BigDecimal value2) {
        return zeroIfNull(value).compareTo(zeroIfNull(value2)) <= 0;
    }

    public boolean equalOrLessThanZero(BigDecimal value) {
        return equalOrLessThan(value, BigDecimal.ZERO);
    }
}
