package com.epam.esm.dao.constant;

public class SqlGiftCertificateQuery {
    public static final String SQL_INSERT_CERTIFICATE = "INSERT INTO gift_certificates (certificate_name," +
            " description, price, duration, create_date) VALUES (?, ?, ?, ?, ?);";

    public static final String SQL_UPDATE_CERTIFICATE_TAG = "INSERT INTO gift_certificates_tags" +
            " (gift_certificate_id_fk, tag_id_fk) VALUES (?, ?);";

    public static final String SQL_SELECT_CERTIFICATE_BY_ID = "SELECT gift_certificate_id, certificate_name," +
            " description, price, duration, create_date, last_update_date, tag_id, tag_name FROM gift_certificates" +
            " LEFT JOIN gift_certificates_tags ON gift_certificate_id = gift_certificate_id_fk" +
            " LEFT JOIN tags ON tag_id = tag_id_fk WHERE gift_certificate_id = ?;";

    public static final String SQL_DELETE_TAGS_FROM_CERTIFICATE_BY_CERTIFICATE_ID = "DELETE FROM gift_certificates_tags" +
            " WHERE gift_certificate_id_fk = ?;";

    public static final String SQL_DELETE_CERTIFICATE_BY_ID = "DELETE FROM gift_certificates" +
            " WHERE gift_certificate_id = ?;";

    public static final String SQL_UPDATE_CERTIFICATE_BY_ID = "UPDATE gift_certificates SET certificate_name = ?," +
            " description = ?, price = ?, duration = ?, last_update_date = ? WHERE gift_certificate_id = ?;";

    public static final String SQL_SELECT_ALL_CERTIFICATES = "SELECT gift_certificate_id, certificate_name," +
            " description, price, duration, create_date, last_update_date, tag_id, tag_name FROM gift_certificates" +
            " LEFT JOIN gift_certificates_tags ON gift_certificate_id = gift_certificate_id_fk" +
            " LEFT JOIN tags ON tag_id = tag_id_fk;";

    public static final String SQL_SELECT_CERTIFICATES_WITH_TAGS = "SELECT gift_certificate_id, certificate_name," +
            " description, price, duration, create_date, last_update_date, tag_id, tag_name FROM gift_certificates" +
            " RIGHT JOIN gift_certificates_tags ON gift_certificate_id = gift_certificate_id_fk" +
            " LEFT JOIN tags ON tag_id = tag_id_fk;";

    private SqlGiftCertificateQuery() {
    }
}
