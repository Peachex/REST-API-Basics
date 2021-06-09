package com.epam.esm.factory;

import org.springframework.util.MultiValueMap;

public interface EntityFactory<T> {
    T create(MultiValueMap<String, String> requestParams);
}
