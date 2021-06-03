package com.epam.rd.davydova.assignment.service.stub.impl;

import com.epam.rd.davydova.assignment.domain.stub.OrderStub;
import com.epam.rd.davydova.assignment.service.stub.IService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceStub implements IService {
    private final OrderStub orderStub;
    private final MessageSource messageSource;

    /**
     * Find stubbed value of Order in RU or EN
     *
     * @param languageTag language tag (en-US or ru-RU)
     * @return string representation of Order object
     */
    @Override
    public String findAll(String languageTag) {
        var result = messageSource.getMessage("message5",
                new Object[]{orderStub.getOrderId(), orderStub.getOrderNumber(), orderStub.getOrderDate(), orderStub.getTotalAmount()},
                "message", Locale.forLanguageTag(languageTag));
        log.info("{}", result);
        return result;
    }
}
