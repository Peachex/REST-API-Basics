package com.epam.esm.validator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GiftCertificateValidatorTest {
    @Test
    public void isNameValidTest() {
        boolean actual = GiftCertificateValidator.isNameValid("Valid Name");
        assertTrue(actual);
    }

    @Test
    public void isDescriptionValidTest() {
        boolean actual = GiftCertificateValidator.isDescriptionValid("");
        assertFalse(actual);
    }

    @Test
    public void isPriceValidTest() {
        boolean actual = GiftCertificateValidator.isPriceValid(new BigDecimal(""));
        assertFalse(actual);
    }
}
