package com.validation;

import lombok.Data;

@Data

public class ErrorDetail {
    private String fieldName;
    private String message;
}
