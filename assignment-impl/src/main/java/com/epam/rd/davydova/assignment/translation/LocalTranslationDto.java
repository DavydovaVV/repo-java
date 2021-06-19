package com.epam.rd.davydova.assignment.translation;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@Profile("local")
@PropertySource("classpath:messages/stub.properties")
public class LocalTranslationDto {

    @Value("#{'${customerValues}'.split(',')}")
    private List<String> customerValues;

    @Value("#{'${supplierValues}'.split(',')}")
    private List<String> supplierValues;

    @Value("#{'${productValues}'.split(',')}")
    private List<String> productValues;

    @Value("#{'${orderValues}'.split(',')}")
    private List<String> orderValues;
}
