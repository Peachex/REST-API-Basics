package com.epam.esm.service;

import com.epam.esm.dto.Tag;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

public interface TagService<T extends Tag> {
    boolean insert(MultiValueMap<String, String> requestParams);

    Optional<T> findById(String id);

    List<T> findAll();

    boolean delete(String id);
}
