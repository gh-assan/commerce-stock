package com.commerce.stock.validation;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.ArrayList;
import java.util.HashMap;

import com.commerce.stock.exception.BaseException;
import com.commerce.stock.exception.HasCode;
import com.commerce.stock.validation.errors.CustomFieldError;
import com.commerce.stock.validation.errors.CustomGlobalError;
import com.commerce.stock.view.ValidationErrors;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler
	@ResponseBody
	ResponseEntity<Object> handleException(BaseException e) {
		
		Map<String,String> responseBody = new HashMap<>();
		 responseBody.put("message",e.getMessage());
	        		
		return new ResponseEntity<Object>(responseBody,  ((HasCode) e).getCode() );
			
    }
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        BindingResult bindingResult = ex.getBindingResult();   
        
        List<CustomFieldError> errors = new ArrayList<CustomFieldError>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
        	errors.add(new CustomFieldError(
				                        fieldError.getField(),
				                        fieldError.getCode(),
				                        fieldError.getRejectedValue())
        					 );
        	
        }
        
        List<CustomGlobalError> customGlobalErrors = new ArrayList<CustomGlobalError>();
        
        for (ObjectError fieldError : bindingResult.getGlobalErrors()) {
        	customGlobalErrors.add(new CustomGlobalError(
				                        fieldError.getCode()
				                        )
        					 );
        	
        }
                
        ValidationErrors validationErrors = new ValidationErrors(errors , customGlobalErrors);
        return new ResponseEntity<>(validationErrors, HttpStatus.UNPROCESSABLE_ENTITY);
        
    }
}
