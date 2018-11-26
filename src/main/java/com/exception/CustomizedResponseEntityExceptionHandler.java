package com.exception;

import com.dto.APIResponseDTO;
import com.validation.ErrorDetail;
import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(Exception.class)
  public final ResponseEntity<APIResponseDTO> handleAllException(Exception ex, WebRequest request) {
    //check web or api
    APIResponseDTO errorDetails = new APIResponseDTO(500, "INTERNAL SERVER ERROR", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

//  @ExceptionHandler(AccessDeniedException.class)
  @ExceptionHandler({ AccessDeniedException.class })
  public final ResponseEntity<APIResponseDTO> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(403, "Access Denied Exception", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return super.handleNoHandlerFoundException(ex, headers, status, request);
  }

  @ExceptionHandler(MethodNotAllowedException.class)
  public final ResponseEntity<APIResponseDTO> handleMethodNotAllowedException(MethodNotAllowedException ex, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(405, "Method not allowed", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(BadHttpRequest.class)
  public final ResponseEntity<APIResponseDTO> handleBadHttpRequest(BadHttpRequest ex, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(400, "BAD REQUEST", null);
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<APIResponseDTO> handleUserCustomException(CustomException cx, WebRequest request) {
    APIResponseDTO errorDetails = new APIResponseDTO(cx.getHttpStatus(), cx.getMessage(), null);
    switch (cx.getHttpStatus()) {
      case 422:
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
      case 403:
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
      case 404:
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
      case 500:
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
      default:
        return new ResponseEntity<>(new APIResponseDTO(400, cx.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

//    return new ResponseEntity<>(new APIResponseDTO(400, "Validation Failed", ex.getBindingResult().getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();

    List<ErrorDetail> errorDetails = new ArrayList<>();
    for (FieldError fieldError : errors) {
      ErrorDetail error = new ErrorDetail();
      error.setFieldName(fieldError.getField());
      error.setMessage(fieldError.getDefaultMessage());
      errorDetails.add(error);
    }


    return new ResponseEntity<>(new APIResponseDTO(400, "Validation Failed", errorDetails), HttpStatus.BAD_REQUEST);

  }

//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//
//    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
//
//    List<ErrorResponse.ErrorDetails> errorDetails = new ArrayList<>();
//    for (FieldError fieldError : errors) {
//      ErrorResponse.ErrorDetails error = new ErrorResponse.ErrorDetails();
//      error.setFieldName(fieldError.getField());
//      error.setMessage(fieldError.getDefaultMessage());
//      errorDetails.add(error);
//    }
//
//    ErrorResponse errorResponse = new ErrorResponse();
//    errorResponse.setErrors(errorDetails);
//
//    return new ResponseEntity<>(new APIResponseDTO(400, "Validation Failed", errorResponse), HttpStatus.BAD_REQUEST);
//
//  }

}