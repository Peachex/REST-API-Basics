package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService<Tag> {
    private final TagDao<Tag> dao;

    @Autowired
    public TagServiceImpl(TagDao<Tag> dao) {
        this.dao = dao;
    }

    @Override
    public boolean insert(MultiValueMap<String, String> requestParams) {
        return false;
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
