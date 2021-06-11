package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

public interface GiftCertificateService<T extends GiftCertificate> {
    boolean insert(T t);

    boolean delete(String id);

    T findById(String id);

    List<GiftCertificate> findCertificatesWithTags();
}
