package org.springframework.samples.petclinic.sfg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.sfg.configurationTest.BaseConfig;
import org.springframework.samples.petclinic.sfg.configurationTest.LaurelConfig;
import org.springframework.samples.petclinic.sfg.services.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
//Permite traer un contexto de Spring
@RunWith(SpringRunner.class)
//Configura el contexto con el que se va a correr la aplicacion
@ActiveProfiles("laurel-test")
@ContextConfiguration(classes = {BaseConfig.class, LaurelConfig.class})
public class HearingInterpreterTest {

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    public void whatIHear() {
        String word = hearingInterpreter.whatIHear();
        assertEquals("Laurel",word);
    }
}