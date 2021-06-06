package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao<GiftCertificate> {
    private static final String SQL_SELECT_ALL_GIFT_CERTIFICATES = "SELECT gift_certificate_id, certificate_name," +
            " description, price, duration, create_date, last_update_date, tag_id, tag_name FROM gift_certificates" +
            " LEFT JOIN gift_certificates_tags ON gift_certificate_id = gift_certificate_id_fk" +
            " LEFT JOIN tags ON tag_id = tag_id_fk;";
    private final JdbcTemplate template;
    private final GiftCertificateMapper giftCertificateMapper;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate template, GiftCertificateMapper giftCertificateMapper) {
        this.template = template;
        this.giftCertificateMapper = giftCertificateMapper;
    }

    @Override
    public boolean add(GiftCertificate giftCertificate) {
        return false;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return template.query(SQL_SELECT_ALL_GIFT_CERTIFICATES, giftCertificateMapper);
    }
}
