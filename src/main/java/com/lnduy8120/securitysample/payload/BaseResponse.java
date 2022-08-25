package com.lnduy8120.securitysample.payload;

public class BaseResponse {

    private int statusCode;
    private String message;

    public BaseResponse (int statusCode, String message) {
    	this.statusCode = statusCode;
    	this.message = message;
    }
    
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
