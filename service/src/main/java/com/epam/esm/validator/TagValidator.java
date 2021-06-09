package com.epam.esm.validator;

import com.epam.esm.dao.constant.SqlTagColumnName;
import org.springframework.util.MultiValueMap;

import java.util.regex.Pattern;

public final class TagValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{1,256}");

    private TagValidator() {
    }

    public static boolean areRequestParamsValid(MultiValueMap<String, String> requestParams) {
        return (requestParams.containsKey(SqlTagColumnName.TAG_NAME) &&
                isNameValid(requestParams.get(SqlTagColumnName.TAG_NAME).get(0)));
    }

    public static boolean isNameValid(String name) {
        return (name != null && NAME_PATTERN.matcher(name).matches());
    }
}
