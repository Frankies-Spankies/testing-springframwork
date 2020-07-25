package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.samples.petclinic.sfg.services.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
//Si no es posible cambiar paquete hay que poner @Profile a las nuevas configuraciones
@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = {HearingInterpreterActiveProfileTest.TestConfig.class})
class HearingInterpreterActiveProfileTest {

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg.services")
    static class TestConfig{}

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHear() {
        String word = hearingInterpreter.whatIHear();
        assertEquals("Yanny", word);
    }
}