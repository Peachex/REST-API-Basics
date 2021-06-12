package com.epam.esm.dao.impl;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.dto.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagDaoImplTest {
    private static final TagDao<Tag> dao = new TagDaoImpl(new DatabaseConfiguration().embeddedDataSource(), new TagMapper());

    @Test
    public void findByNameTest() {
        Optional<Tag> expected = Optional.of(new Tag("#warm"));
        Optional<Tag> actual = dao.findByName("#warm");
        assertEquals(expected, actual);
    }

    @Test
    public void findByIdTest() {
        Optional<Tag> expected = Optional.empty();
        Optional<Tag> actual = dao.findById(12345);
        assertEquals(expected, actual);
    }

    @Test
    public void insertTest() {
        boolean actual = dao.insert(new Tag("#new"));
        assertTrue(actual);
    }

    @Test
    public void deleteTest() {
        boolean actual = dao.delete(12345);
        assertFalse(actual);
    }
}
