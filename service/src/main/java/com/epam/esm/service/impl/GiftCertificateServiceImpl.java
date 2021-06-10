package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.validator.GiftCertificateValidator.isGiftCertificateCreationFormValid;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService<GiftCertificate> {
    private final GiftCertificateDao<GiftCertificate> dao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao<GiftCertificate> dao) {
        this.dao = dao;
    }

    @Override
    public boolean insert(GiftCertificate giftCertificate) {
        boolean result = false;
        if (isGiftCertificateCreationFormValid(giftCertificate)) {
            giftCertificate.setCreateDate(LocalDateTime.now());
            result = dao.insert(giftCertificate);
        }
        return result;
    }

    @Override
    public List<GiftCertificate> findCertificatesWithTags() {
        return dao.findAll().stream().filter(g -> !g.getTags().isEmpty()).collect(Collectors.toList());
    }
}
