package com.springboot.controller.sampleentropy.out;

import com.springboot.controller.common.response.AnaylsisApiResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SampleEntropyApiResponse implements AnaylsisApiResponse {
    private final double[] sEn;
    private final double[] originalSignal;
}
