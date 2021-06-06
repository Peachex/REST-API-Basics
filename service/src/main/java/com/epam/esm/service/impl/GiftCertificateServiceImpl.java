package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateDao<GiftCertificate> dao;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao<GiftCertificate> dao) {
        this.dao = dao;
    }

    @Override
    public List<GiftCertificate> findCertificatesWithTags() {
        return dao.findAll().stream().filter(g -> !g.getTags().isEmpty()).collect(Collectors.toList());
    }
}
