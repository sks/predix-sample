package com.sks.predix.common.exception;

public class CommonCloudException extends RuntimeException {

	private int statusCode;

	public CommonCloudException(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
