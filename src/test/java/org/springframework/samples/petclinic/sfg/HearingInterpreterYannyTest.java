package org.springframework.samples.petclinic.sfg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.sfg.configurationTest.BaseConfig;
import org.springframework.samples.petclinic.sfg.configurationTest.YannyConfig;
import org.springframework.samples.petclinic.sfg.services.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("yanny-test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BaseConfig.class, YannyConfig.class})
public class HearingInterpreterYannyTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    public void whatIHear() {
        String word = hearingInterpreter.whatIHear();
        assertEquals("Yanny",word);
    }
}
