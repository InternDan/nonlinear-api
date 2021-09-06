package com.springboot.controller.sampleentropy;

import com.springboot.controller.sampleentropy.in.SampleEntropyApiRequest;
import com.springboot.controller.sampleentropy.out.SampleEntropyApiResponse;
import com.springboot.service.sampleentropy.SampleEntropyService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleEntropyController {

    @Autowired
    private SampleEntropyService sampleEntropyService;

    @PostMapping(path = "/sampen/compute",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleEntropyApiResponse> compute(@RequestBody SampleEntropyApiRequest request) {
        val result = sampleEntropyService.calculateSampleEntropy(request.getSignal(), request.getM(), request.getR());
        return new ResponseEntity<>(SampleEntropyApiResponse.builder()
                .sEn(result)
                .originalSignal(request.getSignal())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/sampen/requesttemplate")
    public ResponseEntity<SampleEntropyApiRequest> getTemplate(){
        return new ResponseEntity<>(SampleEntropyApiRequest.builder().build(),HttpStatus.OK);
    }
}
