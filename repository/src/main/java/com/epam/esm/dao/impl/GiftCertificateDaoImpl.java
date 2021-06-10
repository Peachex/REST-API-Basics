package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.constant.SqlGiftCertificateQuery;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
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
    @Transactional
    public boolean insert(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isQuerySuccess = template.update(con -> {
            PreparedStatement ps = con.prepareStatement(SqlGiftCertificateQuery.INSERT_GIFT_CERTIFICATE,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, giftCertificate.getName());
            ps.setString(2, giftCertificate.getDescription());
            ps.setBigDecimal(3, giftCertificate.getPrice());
            ps.setInt(4, giftCertificate.getDuration());
            ps.setTimestamp(5, Timestamp.valueOf(giftCertificate.getCreateDate()));
            return ps;
        }, keyHolder) == 1;
        return (isQuerySuccess && updateTag(giftCertificate, keyHolder));
    }

    public boolean updateTag(GiftCertificate giftCertificate, KeyHolder keyHolder) {
        List<Tag> tags = giftCertificate.getTags();
        boolean result = false;
        if (keyHolder.getKey() != null && tags != null && !tags.isEmpty()) {
            result = tags.stream()
                    .allMatch(t -> template.update(SqlGiftCertificateQuery.UPDATE_GIFT_CERTIFICATE_TAG,
                            keyHolder.getKey().longValue(), t.getId()) == 1);
        }
        return result;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return template.query(SQL_SELECT_ALL_GIFT_CERTIFICATES, giftCertificateMapper);
    }
}
