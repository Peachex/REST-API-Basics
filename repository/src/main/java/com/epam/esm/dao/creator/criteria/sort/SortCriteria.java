package com.epam.esm.dao.creator.criteria.sort;

import com.epam.esm.dao.creator.criteria.Criteria;

public abstract class SortCriteria implements Criteria {
    private String columnName;
    private String sortOrdering;

    public SortCriteria(String columnName, String sortOrdering) {
        this.columnName = columnName;
        this.sortOrdering = sortOrdering;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortOrdering() {
        return sortOrdering;
    }

    public void setSortOrdering(String sortOrdering) {
        this.sortOrdering = sortOrdering;
    }
}
