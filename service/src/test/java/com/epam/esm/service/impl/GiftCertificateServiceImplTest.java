package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.service.GiftCertificateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GiftCertificateServiceImplTest {
    private static GiftCertificate giftCertificate;
    private GiftCertificateService<GiftCertificate> service;
    @Mock
    private GiftCertificateDao<GiftCertificate> dao;


    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new GiftCertificateServiceImpl(dao, null);
        giftCertificate = new GiftCertificate(2, "Sand", "Yellow sand", new BigDecimal("2"), 24,
                LocalDateTime.of(2020, 5, 5, 23, 42, 12, 112000000),
                null, new ArrayList<>());
    }

    @Test
    public void findAllTest() {
        List<GiftCertificate> expected = new ArrayList<>();
        expected.add(giftCertificate);
        Mockito.when(dao.findAll()).thenReturn(expected);
        List<GiftCertificate> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        GiftCertificate expected = giftCertificate;
        Mockito.when(dao.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        GiftCertificate actual = service.findById("1");
        assertEquals(expected, actual);
    }

    @Test()
    public void insertTest() {
        assertThrows(InvalidFieldException.class, () -> service.insert(giftCertificate));
    }
}
