package com.springboot.service.sampleentropy;

import lombok.val;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.math3.stat.StatUtils.sum;

@Service
public class SampleEntropyService {

    String distType = "chebychev";

    public double[] calculateSampleEntropy(double[] signal, int m, double r){
        validateInputs(signal,m,r);
        val s = standardizeData(signal);
        //setup needed parameters
        int N = s.length;
        val standardDeviation = new StandardDeviation();
        val std = standardDeviation.evaluate(signal);

        //set up map of matches
        List<double[]> matches = findMatches(s,m);

        return new double[1];
    }

    private List<double[]> findMatches(double[] signal, int m){
        List<double[]> matches = new ArrayList<>();
        for (int i = 0; i < m; i++){
            double[] matchVector = new double[signal.length-i];
            if (i < signal.length - i) {
                for (int j = i; j < signal.length; j++){
                    matchVector[j-i] = signal[j];
                }
            }
            matches.add(matchVector);
        }
        return matches;
    }

    private double[] standardizeData(double[] signal){
        val out = Arrays.stream(signal).map(y -> (y-(sum(signal) / signal.length))).toArray();
        val squared = Arrays.stream(out).map(y -> Math.pow(y,2)).toArray();
        val sqrt = Math.sqrt(sum(squared) / squared.length);
        val ret = Arrays.stream(out).map(y -> (y/sqrt)).toArray();
        return ret;
    }

    private void validateInputs(double[] signal, int m, double r){
        if (m > signal.length){
            throw new RuntimeException();
        }
    }
}
