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

//Mezcla @RunWith y @ContextConfiguration
@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = {HearingInterpreterComponentScanTest.TestConfig.class})
class HearingInterpreterComponentScanTest {

    @Configuration
    //Tambien toma en cuenta las configuraciones que se encuentran en los paquetes de la carpeta test
    //Hay que tomar en cuneta estas configuraciones y aislar en paquetes distintos las configuraciones hechas en la carpeta test
    //Y las de la carpeta main
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