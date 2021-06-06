package com.epam.esm;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        // TODO: 6/6/2021 delete this class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        GiftCertificateServiceImpl service = context.getBean("giftCertificateServiceImpl", GiftCertificateServiceImpl.class);
        service.findCertificatesWithTags().forEach(System.out::println);
    }
}
