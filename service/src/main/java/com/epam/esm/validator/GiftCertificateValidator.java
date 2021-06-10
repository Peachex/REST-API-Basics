package com.epam.esm.validator;

import com.epam.esm.dto.GiftCertificate;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class GiftCertificateValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я0-9]{1,5000}");

    private GiftCertificateValidator() {
    }

    public static boolean isGiftCertificateCreationFormValid(GiftCertificate giftCertificate) {
        return (isNameValid(giftCertificate.getName()) && isDescriptionValid(giftCertificate.getDescription()) &&
                isPriceValid(giftCertificate.getPrice()) && isDurationValid(giftCertificate.getDuration()) &&
                giftCertificate.getCreateDate() == null && giftCertificate.getLastUpdateDate() == null);
    }

    public static boolean isNameValid(String name) {
        return (name != null && NAME_PATTERN.matcher(name).matches());
    }

    public static boolean isDescriptionValid(String description) {
        return (description != null && DESCRIPTION_PATTERN.matcher(description).matches());
    }

    public static boolean isPriceValid(BigDecimal price) {
        return (price != null && price.compareTo(BigDecimal.ZERO) > 0);
    }

    public static boolean isDurationValid(int duration) {
        return duration > 0;
    }
}
