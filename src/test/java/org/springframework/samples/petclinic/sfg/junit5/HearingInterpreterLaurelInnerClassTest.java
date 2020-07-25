package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.services.HearingInterpreter;
import org.springframework.samples.petclinic.sfg.services.LaurelWordProducer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Mezcla @RunWith y @ContextConfiguration
@SpringJUnitConfig(classes = {HearingInterpreterLaurelInnerClassTest.TestConfig.class})
class HearingInterpreterLaurelInnerClassTest {

    @Configuration
    static class TestConfig{
       @Bean
       HearingInterpreter hearingInterpreter(){
           return new HearingInterpreter(new LaurelWordProducer());
       }
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHear() {
        String word = hearingInterpreter.whatIHear();
        assertEquals("Laurel", word);
    }
}