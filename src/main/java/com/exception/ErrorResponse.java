package com.exception;

import java.util.List;

public class ErrorResponse {

        private List<ErrorDetails> errors;

    public void setErrors(List<ErrorDetails> errorDetails) {
        this.errors = errorDetails;
    }

    public static class ErrorDetails {
            private String fieldName;
            private String message;


            public void setFieldName(String field) {
                this.fieldName = field;
            }

            public void setMessage(String defaultMessage) {
                this.message = defaultMessage;
            }
        }
}
