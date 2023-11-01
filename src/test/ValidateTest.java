package test;

import org.junit.Test;
import repositories.Validate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateTest {

    @Test
    public void testValidPassword() {
        assertTrue(Validate.isPasswordValid("Strong@123"));
        assertTrue(Validate.isPasswordValid("SecurePassword#1"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(Validate.isPasswordValid("weak"));
        assertFalse(Validate.isPasswordValid("NoDigits@"));
        assertFalse(Validate.isPasswordValid("NoSpecialChars123"));
        assertFalse(Validate.isPasswordValid("Short1@"));
    }

    @Test
    public void testValidEmail() {
        assertTrue(Validate.validateEmail("test@example.com"));
        assertTrue(Validate.validateEmail("user.name123@sub.domain.org"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(Validate.validateEmail("invalid-email"));
        assertFalse(Validate.validateEmail("no@tld"));
        assertFalse(Validate.validateEmail("spaces @ notallowed.com"));
        assertFalse (Validate.validateEmail("spaces@gmail.c"));

    }

    @Test
    public void testValidName() {
        assertTrue(Validate.validateName("Мастер и Маргарита"));
        assertTrue(Validate.validateName("Master and Margarita"));
    }
    @Test
    public void testInvalidName() {
        assertFalse(Validate.validateName(""));
    }

    @Test
    public void testValidDate() {
        assertTrue(Validate.validateDate(new Date()));
    }

}