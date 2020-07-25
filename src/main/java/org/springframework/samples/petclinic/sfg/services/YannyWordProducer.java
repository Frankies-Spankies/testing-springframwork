package org.springframework.samples.petclinic.sfg.services;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("yanny")
//Sigue siendo nescesario si Laurel no tuviera @Profile
//@Primary
public class YannyWordProducer implements WordProducer {

    @Override
    public String getWord() {
        return "Yanny";
    }
}
