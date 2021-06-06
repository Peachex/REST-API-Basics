package com.epam.esm.dao;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

public interface GiftCertificateDao<T extends GiftCertificate> {
    boolean add(T t);

    List<T> findAll();
}
