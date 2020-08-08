package com.stoom.exception;

import com.stoom.model.Endereco;
import org.testng.annotations.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PostValidator {

    @Test
    public void validator() {

        Endereco endereco = new Endereco();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Endereco>> result = validator.validate(endereco);
        assertEquals(7, result.size());

        List<String> validationErrors = new ArrayList<>();
        result.forEach(e -> validationErrors.add(e.getMessage()));
    }
}
