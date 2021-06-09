package com.epam.esm.service;

import com.epam.esm.dto.Tag;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface TagService<T extends Tag> {
    boolean insert(MultiValueMap<String, String> requestParams);

    T findById(String id);

    List<T> findAll();

    boolean delete(String id);
}
