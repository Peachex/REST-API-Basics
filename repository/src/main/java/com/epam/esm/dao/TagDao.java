package com.epam.esm.dao;

import com.epam.esm.dto.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao<T extends Tag> {
    int insert(T t);

    Optional<T> findById(long id);

    List<T> findAll();

    int delete(long id);
}
