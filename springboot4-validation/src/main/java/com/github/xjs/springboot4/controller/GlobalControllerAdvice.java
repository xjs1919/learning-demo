package com.github.xjs.springboot4.controller;

import com.other.PaginationRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new PaginationRequestValidator());
    }

    public static class PaginationRequestValidator implements SmartValidator {
        @Override
        public boolean supports(Class<?> clazz) {
            return PaginationRequest.class == clazz;
        }
        @Override
        public void validate(Object target, Errors errors, Object... validationHints) {
            validate(target, errors);
        }
        @Override
        public void validate(Object target, Errors errors) {
            PaginationRequest request = (PaginationRequest) target;
            Integer pageIndex = request.getPageIndex();
            if (pageIndex < 0) {
                errors.rejectValue("pageIndex", "pageIndex.invalid", "page index must not less then 0");
            }
            Integer pageSize = request.getPageSize();
            if (pageSize < 1) {
                errors.rejectValue("pageSize", "pageSize.invalid", "page size must not less then 1");
            }
        }
    }

    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatusCode status,
                                                                WebRequest request){
        ProblemDetail problemDetail = ex.getBody();
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> {
                            var message = fieldError.getDefaultMessage();
                            return message == null ? "Invalid value" : message;
                        },
                        (existing, replacement) -> existing));
        problemDetail.setProperty("errors", errors);
        return new ResponseEntity(problemDetail, headers, status);
    }
}
