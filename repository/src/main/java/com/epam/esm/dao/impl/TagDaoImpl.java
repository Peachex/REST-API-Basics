package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.SqlTagQuery;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TagDaoImpl implements TagDao<Tag> {
    private final JdbcTemplate template;
    private final TagMapper mapper;

    @Autowired
    public TagDaoImpl(JdbcTemplate template, TagMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public int insert(Tag tag) {
        return template.update(SqlTagQuery.SQL_INSERT_TAG, tag.getName());
    }

    @Override
    public Optional<Tag> findById(long id) {
        return template.query(SqlTagQuery.SQL_SELECT_TAG_BY_ID, mapper, new Object[]{id}).stream().findAny();
    }

    @Override
    public List<Tag> findAll() {
        return template.query(SqlTagQuery.SQL_SELECT_ALL_TAGS, mapper);
    }

    @Override
    public int delete(long id) {
        return template.update(SqlTagQuery.SQL_DELETE_TAG_BY_ID, id);
    }
}
