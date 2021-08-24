package com.springboot.controller;

import com.springboot.service.sampleentropy.SampleEntropyService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnalysisController {

	@Autowired
	private SampleEntropyService sampleEntropyService;

	@GetMapping("/")
	public String index() {
		val result = sampleEntropyService.calculateSampleEntropy(createDubArray(1000),5,0.2);

		return "This will soon analyze things!";
	}

	private double[] createDubArray(int len){
		val dub = new double[len];
		for (int i = 0; i < len; i++){
			dub[i] = Math.random() * 10;
		}
		return dub;
	}

}