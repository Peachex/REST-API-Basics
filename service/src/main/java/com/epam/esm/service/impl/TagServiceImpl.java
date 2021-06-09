package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.esm.validator.TagValidator.isNameValid;

@Service
public class TagServiceImpl implements TagService<Tag> {
    private final TagDao<Tag> dao;

    @Autowired
    public TagServiceImpl(TagDao<Tag> dao) {
        this.dao = dao;
    }

    @Override
    public boolean insert(Tag tag) {
        return (isNameValid(tag.getName()) && dao.insert(tag) == 1);
    }

    @Override
    public Tag findById(String id) {
        try {
            return dao.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid tag id (id = " + id + ")");
        }
    }

    @Override
    public List<Tag> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean delete(String id) {
        try {
            return dao.delete(Long.parseLong(id)) == 1;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid tag id (id = " + id + ")");
        }
    }
}
