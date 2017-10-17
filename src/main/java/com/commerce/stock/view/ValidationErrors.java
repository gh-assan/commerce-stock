package com.commerce.stock.view;

import java.util.List;

import com.commerce.stock.validation.errors.CustomFieldError;
import com.commerce.stock.validation.errors.CustomGlobalError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrors {
	
	private List<CustomFieldError> customFieldErrors;
    private List<CustomGlobalError> globalErrors;

    public ValidationErrors(List<CustomFieldError> customFieldErrors, List<CustomGlobalError> globalErrors) {
        this.customFieldErrors = customFieldErrors;
        this.globalErrors = globalErrors;
    }
    
    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("FieldErrors")
    public List<CustomFieldError> getFieldErrors() {
        return customFieldErrors;
    }

    
    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("Errors")
    public List<CustomGlobalError> getGlobalErrors() {
        return globalErrors;
    }

    @JsonProperty("status-code")
	public String getStatusCode() {
		return "204";
	}
    

}
