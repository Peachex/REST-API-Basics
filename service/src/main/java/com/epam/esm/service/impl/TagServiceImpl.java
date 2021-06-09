package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import com.epam.esm.factory.EntityFactory;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService<Tag> {
    private final TagDao<Tag> dao;
    private final EntityFactory<Optional<Tag>> factory;

    @Autowired
    public TagServiceImpl(TagDao<Tag> dao, EntityFactory<Optional<Tag>> factory) {
        this.dao = dao;
        this.factory = factory;
    }

    @Override
    public boolean insert(MultiValueMap<String, String> requestParams) {
        Optional<Tag> tag = factory.create(requestParams);
        return (tag.isPresent() && dao.insert(tag.get()) == 1);
    }

    @Override
    public Optional<Tag> findById(String id) {
        Optional<Tag> tag;
        try {
            tag = dao.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            tag = Optional.empty();
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean delete(String id) {
        boolean result;
        try {
            result = dao.delete(Long.parseLong(id)) == 1;
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
