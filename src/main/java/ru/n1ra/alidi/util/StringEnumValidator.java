package ru.n1ra.alidi.util;

import ru.n1ra.alidi.annotation.StringEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class StringEnumValidator implements ConstraintValidator<StringEnum, String> {
    private Set<String> enumNames;

    @Override
    public void initialize(StringEnum stringEnumeration) {
        Class<? extends Enum<?>> enumSelected = stringEnumeration.enumClass();
        enumNames = getNamesSet(enumSelected);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || enumNames.contains(value.toUpperCase());
    }

    private static Set<String> getNamesSet(Class<? extends Enum<?>> e) {
        Enum<?>[] enums = e.getEnumConstants();
        Set<String> names = new HashSet<>();
        for (Enum<?> elem : enums) {
            names.add(elem.name());
        }

        return names;
    }
}
