package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;
import com.epam.esm.exception.InvalidFieldException;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import static com.epam.esm.validator.GiftCertificateValidator.isGiftCertificateCreationFormValid;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService<GiftCertificate> {
    private final GiftCertificateDao<GiftCertificate> dao;
    private final TagService<Tag> tagService;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao<GiftCertificate> dao, TagService<Tag> tagService) {
        this.dao = dao;
        this.tagService = tagService;
    }

    @Transactional
    @Override
    public boolean insert(GiftCertificate giftCertificate) {
        if (isGiftCertificateCreationFormValid(giftCertificate)) {
            giftCertificate.setCreateDate(LocalDateTime.now());
            if (giftCertificate.getTags() != null && saveNewTags(giftCertificate, tagService.findAll())) {
                List<Tag> tagsWithId = new ArrayList<>();
                giftCertificate.getTags().forEach(t -> tagsWithId.add(tagService.findByName(t.getName())));
                giftCertificate.setTags(tagsWithId);
            }
            return dao.insert(giftCertificate);
        } else {
            throw new InvalidFieldException("1", "Invalid gift certificate: " + giftCertificate);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Optional<GiftCertificate> giftCertificateOptional = dao.findById(Long.parseLong(id));
            if (giftCertificateOptional.isPresent()) {
                GiftCertificate giftCertificate = giftCertificateOptional.get();
                if (giftCertificate.getTags() != null && !giftCertificate.getTags().isEmpty()) {
                    dao.disconnectAllTags(giftCertificate.getId());
                }
                return dao.delete(giftCertificate.getId());
            } else {
                throw new ResourceNotFoundException("1", "Requested resource not found (id = " + id + ")");
            }
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("1", "Invalid tag id (id = " + id + ")");
        }
    }

    @Override
    public GiftCertificate findById(String id) {
        try {
            return dao.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("1", "Requested" +
                    " resource not found (id = " + id + ")"));
        } catch (NumberFormatException e) {
            throw new InvalidFieldException("1", "Invalid tag id (id = " + id + ")");
        }
    }

    @Override
    public List<GiftCertificate> findCertificatesWithTags() {
        return dao.findAll().stream()
                .filter(g -> !g.getTags().isEmpty())
                .collect(Collectors.toList());
    }

    private boolean saveNewTags(GiftCertificate giftCertificate, List<Tag> existingTags) {
        return giftCertificate.getTags().stream()
                .filter(t -> !existingTags.contains(t))
                .allMatch(tagService::insert);
    }
}
