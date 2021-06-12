package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.Tag;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagServiceImpl {
    @Mock
    private TagDao<Tag> dao;
    private TagService<Tag> service;

    @BeforeAll
    
}
