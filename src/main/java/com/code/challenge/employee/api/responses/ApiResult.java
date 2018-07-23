package com.code.challenge.employee.api.responses;

import java.util.List;

import com.code.challenge.employee.api.model.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResult {

	private int count;
	
	@JsonProperty(value = "total_pages")
	private int totalPages;

	@JsonProperty(value = "next_page_url")
	private String nextPageUrl;

	@JsonProperty(value = "previous_page_url")
	private String previousPageUrl;

	@JsonProperty(value = "employees_results")
	private List<Employee> employees;

	public ApiResult(int count, int totalPages, String nextPageUrl, String previousPageUrl, List<Employee> employees) {
		this.count = count;
		this.totalPages = totalPages;
		this.nextPageUrl = nextPageUrl;
		this.previousPageUrl = previousPageUrl;
		this.employees = employees;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getNextPageUrl() {
		return nextPageUrl;
	}

	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	public String getPreviousPageUrl() {
		return previousPageUrl;
	}

	public void setPreviousPageUrl(String previousPageUrl) {
		this.previousPageUrl = previousPageUrl;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
