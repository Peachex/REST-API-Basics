package com.epam.esm.dao.creator.criteria.search;

import com.epam.esm.dao.creator.criteria.Criteria;

public abstract class SearchCriteria implements Criteria {
    private String columnName;
    private String value;

    public SearchCriteria(String columnName, String value) {
        this.columnName = columnName;
        this.value = value.toLowerCase();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
