package com.epam.esm.dao;

import com.epam.esm.dto.Tag;

import java.util.List;

public interface TagDao<T extends Tag> {
    int insert(T t);

    T findById(long id);

    List<T> findAll();

    int delete(long id);
}
