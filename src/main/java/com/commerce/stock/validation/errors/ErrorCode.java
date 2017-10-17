package com.commerce.stock.validation.errors;

public enum ErrorCode {
	
	OUTDATED_STOCK("Outdated stock, because a newer stock was processed first");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
