package com.tgid.teste.junior.anottation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CnpjValidator.class)

public @interface Cnpj {
    String message() default "Invalid CNPJ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
