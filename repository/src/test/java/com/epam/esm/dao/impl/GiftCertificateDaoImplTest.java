package com.epam.esm.dao.impl;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.creator.SqlGiftCertificateQueryCreator;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dto.GiftCertificate;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GiftCertificateDaoImplTest {
    private static final GiftCertificateDao<GiftCertificate> dao = new GiftCertificateDaoImpl
            (new DatabaseConfiguration().embeddedDataSource(), new GiftCertificateMapper(),
                    new SqlGiftCertificateQueryCreator());

    @Test
    public void insertTest() {
        boolean actual = dao.insert(new GiftCertificate(6, "Test", "Test", new BigDecimal("1"), 2,
                LocalDateTime.of(2021, 5, 5, 23, 42, 12, 112000000),
                null, new ArrayList<>()));
        assertTrue(actual);
    }

    @Test
    public void deleteTest() {
        boolean actual = dao.delete(12345);
        assertFalse(actual);
    }

    @Test
    public void findByIdTest() {
        Optional<GiftCertificate> expected = Optional.of(new GiftCertificate(2, "Sand", "Yellow sand", new BigDecimal("2"), 24,
                LocalDateTime.of(2020, 5, 5, 23, 42, 12, 112000000),
                null, new ArrayList<>()));
        Optional<GiftCertificate> actual = dao.findById(2);
        assertEquals(expected, actual);
    }

    @Test
    public void disconnectAllTagsTest() {
        boolean actual = dao.disconnectAllTags(12345);
        assertFalse(actual);
    }
}
