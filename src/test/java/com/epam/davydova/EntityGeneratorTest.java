package com.epam.davydova;

import com.epam.davydova.pojos.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * This is the class to test EntityGenerator class
 */
@ExtendWith(MockitoExtension.class)
public class EntityGeneratorTest {

    @Mock
    private CheckPojos checkPojos;

    @Test
    public void generateEntitiesTest() {
        checkPojos.checkEntityAnnotation(Person.class);

        verify(checkPojos).checkEntityAnnotation(Person.class);
    }
}