package com.code.challenge.employee.api.responses;

import java.util.Date;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {

	@JsonProperty(value = "http_status")
	private HttpStatus httpStatus;

	@JsonProperty(value = "http_status_code")
	private int httpStatusCode;

	@JsonProperty(value = "response_date")
	private Date responseDate;

	@JsonProperty(value = "called_service")
	private String calledService;

	@JsonProperty(value = "api_response")
	private T response;

	public ApiResponse(HttpStatus httpStatus, int httpStatusCode, String calledService, T response) {
		this.responseDate = new Date();
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatusCode;
		this.setCalledService(calledService);
		this.response = response;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getCalledService() {
		return calledService;
	}

	public void setCalledService(String calledService) {
		this.calledService = calledService;
	}
}
