package com.epam.esm.dao.constant;

public class SqlTagQuery {
    public static final String SQL_SELECT_ALL_TAGS = "SELECT tag_id, tag_name FROM tags;";

    public static final String SQL_SELECT_TAG_BY_ID = "SELECT tag_id, tag_name FROM tags WHERE tag_id = ?;";

    public static final String SQL_SELECT_TAG_BY_NAME = "SELECT tag_id, tag_name FROM tags WHERE tag_name = ?;";

    public static final String SQL_DELETE_TAG_BY_ID = "DELETE FROM tags WHERE tag_id = ?;";

    public static final String SQL_INSERT_TAG = "INSERT INTO tags (tag_name) VALUES (?);";

    private SqlTagQuery() {
    }
}
