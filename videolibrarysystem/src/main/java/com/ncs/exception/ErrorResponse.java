package com.ncs.exception;

import java.util.Date;

public class ErrorResponse {

	private String errorCode;
	private String errorDetails;
	private Date timestamp;
	
	public ErrorResponse(String errorCode, String errorDetails, Date timestamp) {
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
