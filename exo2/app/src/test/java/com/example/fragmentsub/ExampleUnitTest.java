package com.example.fragmentsub;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void buildInterestsString_isCorrect() {
        assertEquals("sport, lecture", User.buildInterestsString(true, false, true));
        assertEquals("", User.buildInterestsString(false, false, false));
    }

    @Test
    public void loginValidation_isCorrect() {
        assertTrue(ValidationUtils.isLoginValid("a123"));
        assertTrue(ValidationUtils.isLoginValid("Abcdef_12"));
        assertFalse(ValidationUtils.isLoginValid("1abc"));
        assertFalse(ValidationUtils.isLoginValid("abcdefghijk"));
        assertFalse(ValidationUtils.isLoginValid("ab-cd"));
    }

    @Test
    public void passwordValidation_isCorrect() {
        assertTrue(ValidationUtils.isPasswordValid("123456"));
        assertFalse(ValidationUtils.isPasswordValid("12345"));
        assertFalse(ValidationUtils.isPasswordValid("1234567"));
        assertFalse(ValidationUtils.isPasswordValid(""));
    }
}