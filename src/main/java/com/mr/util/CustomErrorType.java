package com.mr.util;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomErrorType {

    @JsonProperty("error_message")
    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
