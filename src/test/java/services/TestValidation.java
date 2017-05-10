package services;

import org.junit.Test;
import ua.test.utils.Validation;

import static org.junit.Assert.assertTrue;

public class TestValidation {

    @Test
    public void emailIsValid() {
        final String email = "test@i.ua";
        assertTrue(Validation.isEmailValid(email));
    }

    @Test
    public void emailIsNotValid() {
        final String email = "test@i.i.i.ua";
        assertTrue(!Validation.isEmailValid(email));
    }
}
