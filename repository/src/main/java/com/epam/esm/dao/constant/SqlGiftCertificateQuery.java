package com.epam.esm.dao.constant;

public class SqlGiftCertificateQuery {
    public static final String INSERT_GIFT_CERTIFICATE = "INSERT INTO gift_certificates (certificate_name," +
            " description, price, duration, create_date) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_GIFT_CERTIFICATE_TAG = "INSERT INTO gift_certificates_tags" +
            " (gift_certificate_id_fk, tag_id_fk) VALUES (?, ?);";

    private SqlGiftCertificateQuery() {
    }
}
