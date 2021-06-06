package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificate;

import java.util.List;

public interface GiftCertificateService {
    List<GiftCertificate> findCertificatesWithTags();
}
