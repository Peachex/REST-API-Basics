package com.epam.esm.mapper;

import com.epam.esm.dto.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
    private static final String ID = "gift_certificate_id";
    private static final String NAME = "certificate_name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";
    private static final String CREATE_DATE = "create_date";
    private static final String LAST_UPDATE_DATE = "last_update_date";


    @Override
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(ID);
        String name = rs.getString(NAME);
        String description = rs.getString(DESCRIPTION);
        BigDecimal price = rs.getBigDecimal(PRICE);
        int duration = rs.getInt(DURATION);
        LocalDateTime createDate = rs.getObject(CREATE_DATE, LocalDateTime.class);
        LocalDateTime lastUpdateDate = rs.getObject(LAST_UPDATE_DATE, LocalDateTime.class);

        return new GiftCertificate(id, name, description, price, duration, createDate, lastUpdateDate, null);
    }
}
