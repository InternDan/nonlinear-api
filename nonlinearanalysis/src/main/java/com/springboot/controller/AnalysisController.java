package com.springboot.controller;

import com.springboot.service.sampleentropy.SampleEntropyService;
import com.springboot.util.PinkNoise;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Random;

@RestController
public class AnalysisController {

	@Autowired
	private SampleEntropyService sampleEntropyService;

	@GetMapping("/")
	public String index() {
		val result = sampleEntropyService.calculateSampleEntropy(createPinkNoiseArray(10000),5,0.2);

		return "This will soon analyze things! Pink noise resulted in " + Arrays.toString(result);
	}

	private double[] createRandomArray(int len){
		val dub = new double[len];
		for (int i = 0; i < len; i++){
			dub[i] = Math.random() * 10;
		}
		return dub;
	}

	private double[] createSamesiesArray(int len){
		val dub = new double[len];
		for (int i = 0; i < len; i++){
			dub[i] = 0.3;
		}
		return dub;
	}

	private double[] createPinkNoiseArray(int len){
		val dub = new double[len];
		PinkNoise pinkNoise = new PinkNoise(1,10, new Random());
		for (int i = 0; i < len; i++){
			dub[i] = pinkNoise.nextValue();
		}
		return dub;
	}

}