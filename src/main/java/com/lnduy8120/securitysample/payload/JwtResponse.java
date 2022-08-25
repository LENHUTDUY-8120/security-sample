package com.lnduy8120.securitysample.payload;

public class JwtResponse extends BaseResponse{

	private String token;
	private String type = "Bearer";
	
	public JwtResponse(int statusCode, String message, String token) {
		super(statusCode, message);
		this.token = token;
	}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
