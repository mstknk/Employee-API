package com.code.challenge.employee.api.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "employee", indexes = { @Index(columnList = "uuid", name = "employee_uuid_hidx") })
public class Employee {

	@Id
	@GeneratedValue
	@Column(name = "uuid", unique = true)
	private UUID uuid;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "birthday")
	@JsonProperty
	private LocalDate birthday;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonProperty
	private Set<Hobby> hobbies ;

	public Employee() {
	}

	public Employee(String fullName, String email, LocalDate birthday) {
		this.fullName = fullName;
		this.email = email;
		this.birthday=birthday;

	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public UUID getUuid() {
		return uuid;
	}

	public Set<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}


}
