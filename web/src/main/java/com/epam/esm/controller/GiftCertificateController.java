package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private final GiftCertificateService service;

    @Autowired
    public GiftCertificateController(GiftCertificateService service) {
        this.service = service;
    }

    @GetMapping
    public List<GiftCertificate> findCertificatesWithTags() {
        return service.findCertificatesWithTags();
    }
}
