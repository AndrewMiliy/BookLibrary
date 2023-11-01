package repositories;

import org.junit.Test;

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
}