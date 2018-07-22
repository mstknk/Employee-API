package com.code.challenge.employee.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "hobby")
public class Hobby {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "hobby_name")
	private String hobbyName;

	@ManyToOne
	@JoinColumn(name = "employee", nullable = false)
	@JsonIgnore
	private Employee employee;

	public Hobby(String hobbyName, Employee employee) {
		this.setHobbyName(hobbyName);
		this.employee = employee;
	}

	public Hobby() {
	}

	public Long getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((getHobbyName() == null) ? 0 : getHobbyName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hobby other = (Hobby) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (getHobbyName() == null) {
			if (other.getHobbyName() != null)
				return false;
		} else if (!getHobbyName().equals(other.getHobbyName()))
			return false;
		return true;
	}

	public String getHobbyName() {
		return hobbyName;
	}

	public void setHobbyName(String hobbyName) {
		this.hobbyName = hobbyName;
	}

}
