package com.springboot.controller.sampleentropy.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SampleEntropyApiRequest {
    private double[] signal;
    private Integer M;
    private double r;
}
