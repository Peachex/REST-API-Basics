package com.epam.esm.dao.mapper;

import com.epam.esm.dto.GiftCertificate;
import com.epam.esm.dto.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class GiftCertificateMapper implements ResultSetExtractor<List<GiftCertificate>> {
    private static final String GIFT_CERTIFICATE_ID = "gift_certificate_id";
    private static final String NAME = "certificate_name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";
    private static final String CREATE_DATE = "create_date";
    private static final String LAST_UPDATE_DATE = "last_update_date";
    private static final String TAG_ID = "tag_id";
    private static final String TAG_NAME = "tag_name";

    @Override
    public List<GiftCertificate> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        rs.next();
        while (!rs.isAfterLast()) {
            long giftCertificateId = rs.getLong(GIFT_CERTIFICATE_ID);
            String giftCertificateName = rs.getString(NAME);
            String description = rs.getString(DESCRIPTION);
            BigDecimal price = rs.getBigDecimal(PRICE);
            int duration = rs.getInt(DURATION);
            LocalDateTime createDate = rs.getObject(CREATE_DATE, LocalDateTime.class);
            LocalDateTime lastUpdateDate = rs.getObject(LAST_UPDATE_DATE, LocalDateTime.class);

            List<Tag> tags = new ArrayList<>();
            do {
                long tagId = rs.getLong(TAG_ID);
                String tagName = rs.getString(TAG_NAME);
                if (tagId != 0 && tagName != null) {
                    tags.add(new Tag(tagId, tagName));
                }
            } while (rs.next() && rs.getLong(GIFT_CERTIFICATE_ID) == giftCertificateId);
            giftCertificates.add(new GiftCertificate(giftCertificateId, giftCertificateName, description, price, duration, createDate, lastUpdateDate, tags));
        }
        return giftCertificates;
    }
}
