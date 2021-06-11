package com.epam.esm.service;

import com.epam.esm.dto.Tag;

import java.util.List;

public interface TagService<T extends Tag> {
    boolean insert(Tag tag);

    T findById(String id);

    T findByName(String name);

    List<T> findAll();

    List<T> findTagsConnectedToCertificate(String certificateId);

    boolean delete(String id);
}
