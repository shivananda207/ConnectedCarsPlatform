package com.demo.rabitmq;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Employee.class)
public class MessageFormat {

	private Boolean lidStatus;
	private String city;

	public Boolean getLidStatus() {
		return lidStatus;
	}
	public void setLidStatus(Boolean lidStatus) {
		this.lidStatus = lidStatus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "MessageFormat [lidStatus=" + lidStatus + ", city=" + city + "]";
	}

}