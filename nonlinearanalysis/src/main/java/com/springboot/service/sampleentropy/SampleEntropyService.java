package com.springboot.service.sampleentropy;

import Jama.Matrix;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import java.util.Arrays;

import static org.apache.commons.math3.stat.StatUtils.sum;

@Service
@Slf4j
public class SampleEntropyService {

    String distType = "chebychev";

    public double[] calculateSampleEntropy(double[] signal, int M, double r){
        validateInputs(signal,M,r);
        val s = standardizeData(signal);
        val sEn = sampEn(s,M,r);
        return sEn;
    }


    private double[] standardizeData(double[] signal){
        val mean = sum(signal) / signal.length;
        val out = Arrays.stream(signal).map(y -> y-mean).toArray();
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

    private double[] sampEn(double[] signal, int M, double r){
        //initialize arrays
        int n = signal.length;
        val lastRun = new double[n];
        val run = new double[n];
        val A = new double[M];
        val B = new double[M];
        for (int i = 0; i < n; i++){
            val nj = n-i;
            val y1 = signal[i];
            for (int jj = 0; jj < nj; jj++){
                int j = jj + i;
                if (Math.abs(signal[j] - y1) < r){
                    run[jj] = lastRun[jj] + 1;
                    val M1 = Math.min(M,run[jj]);
                    for (int m = 0; m < M1; m++){
                        A[m] = A[m] + 1;
                        if (j < n){
                            B[m] = B[m] + 1;
                        }
                    }
                } else {
                    run[jj] = 0;
                }
            }
            for (int j = 0; j < nj; j++){
                lastRun[j] = run[j];
            }
        }
        val N = n * (n-1) / 2;
        val newB = new double[M];
        newB[0] = N;
        for (int i = 1; i < M; i++){
            newB[i] = B[i-1];
        }
        Matrix matrixA = new Matrix(A,1);
        Matrix matrixB = new Matrix(newB,1);

        val e = matrixA.arrayRightDivide(matrixB).getArray()[0];
        return Arrays.stream(e).map(a -> (-1 * Math.log(a))).toArray();
    }
}
