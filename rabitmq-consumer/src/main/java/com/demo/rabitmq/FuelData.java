package com.demo.rabitmq;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FuelData {
	
	public String district;
	public List<Product> products;
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
