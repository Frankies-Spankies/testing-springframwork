package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.sfg.configurationTest.BaseConfig;
import org.springframework.samples.petclinic.sfg.configurationTest.LaurelConfig;
import org.springframework.samples.petclinic.sfg.configurationTest.YannyConfig;
import org.springframework.samples.petclinic.sfg.services.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

//Mezcla @RunWith y @ContextConfiguration
@ActiveProfiles("laurel-test")
@SpringJUnitConfig(classes = {BaseConfig.class , LaurelConfig.class, YannyConfig.class})
class HearingInterpreterLaurelTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHear() {
        String word = hearingInterpreter.whatIHear();
        assertEquals("Laurel", word);
    }
}