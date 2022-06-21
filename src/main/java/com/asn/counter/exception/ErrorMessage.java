package com.asn.counter.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorMessage {

    private long timestamp;
    private long status;
    private String message;
    private String path;
    private String method;
}
