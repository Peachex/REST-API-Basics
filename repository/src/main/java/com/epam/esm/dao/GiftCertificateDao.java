package com.epam.esm.dao;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao<T extends GiftCertificate> {
    boolean insert(T t);

    boolean delete(long id);

    boolean disconnectAllTags(long id);

    Optional<GiftCertificate> findById(long id);

    List<T> findAll();
}
