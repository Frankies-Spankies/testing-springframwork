package org.springframework.samples.petclinic.sfg.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"extenalized", "laurel-properties"})
@Primary //Aqui no hay mucho problema por que todos los beans tienen un perfil
public class PropertiesWordProducer implements WordProducer{

    private String word;

    @Value("${say.word}")
    public void setWord(String word){
        this.word = word;
    }

    @Override
    public String getWord() {
        return word;
    }
}
