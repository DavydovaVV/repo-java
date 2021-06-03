package com.epam.rd.davydova.assignment.service.stub.impl;

import com.epam.rd.davydova.assignment.domain.stub.CustomerStub;
import com.epam.rd.davydova.assignment.service.stub.IService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceStub implements IService {
    private final CustomerStub customerStub;
    private final MessageSource messageSource;

    /**
     * Find stubbed value of Customer in RU or EN
     *
     * @param languageTag language tag (en-US or ru-RU)
     * @return string representation of Customer object
     */
    @Override
    public String findAll(String languageTag) {
        var result = messageSource.getMessage("message2",
                new Object[]{customerStub.getCustomerId(), customerStub.getCustomerName(), customerStub.getPhone()},
                "message", Locale.forLanguageTag(languageTag));
        log.info("{}", result);
        return result;
    }
}
