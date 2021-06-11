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

import static com.epam.esm.validator.GiftCertificateValidator.areGiftCertificateTagsValid;
import static com.epam.esm.validator.GiftCertificateValidator.isDescriptionValid;
import static com.epam.esm.validator.GiftCertificateValidator.isDurationValid;
import static com.epam.esm.validator.GiftCertificateValidator.isGiftCertificateCreationFormValid;
import static com.epam.esm.validator.GiftCertificateValidator.isNameValid;
import static com.epam.esm.validator.GiftCertificateValidator.isPriceValid;

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

    @Transactional
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

    @Transactional
    @Override
    public boolean update(String id, GiftCertificate newGiftCertificate) {
        try {
            GiftCertificate oldGiftCertificate = dao.findById(Long.parseLong(id)).orElseThrow(() ->
                    new ResourceNotFoundException("1", "Requested resource not found (id = " + id + ")"));
            if (updateGiftCertificateFields(oldGiftCertificate, newGiftCertificate) &&
                    saveNewTags(oldGiftCertificate, tagService.findAll())) {
                oldGiftCertificate.setLastUpdateDate(LocalDateTime.now());
                List<Tag> tagsConnectedToCertificate = tagService.findTagsConnectedToCertificate(id);
                List<Tag> tagsNeedToBeConnected = oldGiftCertificate.getTags().stream()
                        .filter(t -> !tagsConnectedToCertificate.contains(t))
                        .collect(Collectors.toList());
                dao.connectTags(tagsNeedToBeConnected, oldGiftCertificate.getId());
                return dao.update(oldGiftCertificate);
            } else {
                throw new InvalidFieldException("1", "Invalid gift certificate: " + newGiftCertificate);
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

    private boolean updateGiftCertificateFields(GiftCertificate oldGiftCertificate, GiftCertificate newGiftCertificate) {
        boolean result = false;
        if (isNameValid(newGiftCertificate.getName())) {
            oldGiftCertificate.setName(newGiftCertificate.getName());
            result = true;
        }
        if (isDescriptionValid(newGiftCertificate.getDescription())) {
            oldGiftCertificate.setDescription(newGiftCertificate.getDescription());
            result = true;
        }
        if (isPriceValid(newGiftCertificate.getPrice())) {
            oldGiftCertificate.setPrice(newGiftCertificate.getPrice());
            result = true;
        }
        if (isDurationValid(newGiftCertificate.getDuration())) {
            oldGiftCertificate.setDuration(newGiftCertificate.getDuration());
            result = true;
        }
        if (areGiftCertificateTagsValid(newGiftCertificate.getTags())) {
            oldGiftCertificate.setTags(newGiftCertificate.getTags());
            result = true;
        }
        return result;
    }
}
