package br.com.fiap.iottu_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlacaValidador implements ConstraintValidator<PlacaValida, String> {

    private static final String PADRAO_PLACA = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$|^[A-Z]{3}[0-9]{4}$";

    @Override
    public boolean isValid(String placa, ConstraintValidatorContext context) {
        if (placa == null) return false;
        return placa.toUpperCase().matches(PADRAO_PLACA);
    }
}