package org.springframework.samples.petclinic.sfg.services;

import org.springframework.stereotype.Service;

@Service
public class HearingInterpreter {

    private final WordProducer wordProducer;

    public HearingInterpreter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String whatIHear(){
        String word = this.wordProducer.getWord();
        System.out.println(word);
        return word;
    }
}
