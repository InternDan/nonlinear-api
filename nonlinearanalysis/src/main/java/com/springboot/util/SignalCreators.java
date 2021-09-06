package com.springboot.util;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Random;

@UtilityClass
public class SignalCreators {

    public double[] createRandomArray(int len){
        val dub = new double[len];
        for (int i = 0; i < len; i++){
            dub[i] = Math.random() * 10;
        }
        return dub;
    }

    public double[] createSamesiesArray(int len){
        val dub = new double[len];
        for (int i = 0; i < len; i++){
            dub[i] = 0.3;
        }
        return dub;
    }

    public double[] createPinkNoiseArray(int len){
        val dub = new double[len];
        PinkNoise pinkNoise = new PinkNoise(1,10, new Random());
        for (int i = 0; i < len; i++){
            dub[i] = pinkNoise.nextValue();
        }
        return dub;
    }

    public double[] createBrownNoiseArray(int len){
        val dub = new double[len];
        PinkNoise pinkNoise = new PinkNoise(2,10, new Random());
        for (int i = 0; i < len; i++){
            dub[i] = pinkNoise.nextValue();
        }
        return dub;
    }
}
