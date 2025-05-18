package br.com.fiap.iottu_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlacaValidador.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface PlacaValida {
    String message() default "Placa inválida. Use o formato ABC1234 ou ABC1D23.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
