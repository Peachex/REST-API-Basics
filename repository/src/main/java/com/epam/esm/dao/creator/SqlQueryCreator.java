package com.epam.esm.dao.creator;

import com.epam.esm.dao.creator.criteria.Criteria;

import java.util.List;

public interface SqlQueryCreator {
    String createQuery(List<Criteria> criteriaList);
}
