package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.services.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("classpath:laurel.properties")
@ActiveProfiles("laurel-properties")
@SpringJUnitConfig(classes = {PropertiesWordProducerLaurelTest.TestConfig.class})
class PropertiesWordProducerLaurelTest {

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg.services")
    static class TestConfig{} //La clase debe de ser static por ser interna

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHear() {
        String word = hearingInterpreter.whatIHear();
        assertEquals("laURel", word);
    }

}