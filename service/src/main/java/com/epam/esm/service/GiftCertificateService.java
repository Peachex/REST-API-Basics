package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

public interface GiftCertificateService<T extends GiftCertificate> {
    boolean insert(T t);

    boolean delete(String id);

    boolean update(String id, GiftCertificate giftCertificate);

    T findById(String id);

    List<GiftCertificate> findCertificatesWithTags();
}
