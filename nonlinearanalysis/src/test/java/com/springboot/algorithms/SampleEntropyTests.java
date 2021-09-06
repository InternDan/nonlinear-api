package com.springboot.algorithms;

import com.springboot.service.sampleentropy.SampleEntropyService;
import com.springboot.util.SignalCreators;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleEntropyTests {

    SampleEntropyService sampleEntropyService = new SampleEntropyService();

    @Test
    public void brownNoiseReflectsCorrectRange(){
        val signal = SignalCreators.createBrownNoiseArray(10000);
        val sEn = sampleEntropyService.calculateSampleEntropy(signal,5,0.2);
        val last = sEn[sEn.length-1];

        Assertions.assertTrue(last < 0.1);
    }

    @Test
    public void pinkNoiseReflectsCorrectRange(){
        val signal = SignalCreators.createPinkNoiseArray(10000);
        val sEn = sampleEntropyService.calculateSampleEntropy(signal,5,0.2);
        val last = sEn[sEn.length-1];

        Assertions.assertTrue(last < 0.9 && last > 0.6);
    }

    @Test
    public void randomNoiseReflectsCorrectRange(){
        val signal = SignalCreators.createRandomArray(10000);
        val sEn = sampleEntropyService.calculateSampleEntropy(signal,5,0.2);
        val last = sEn[sEn.length-1];

        Assertions.assertTrue(last < 0.6 && last > 0.4);
    }
}
