package com.demo.rabitmq;

import org.springframework.stereotype.Component;

@Component
public class Product {
	public String productName;
	public String productPrice;
	public String productCurrency;
	public String priceChange;
	public String priceChangeSign;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductCurrency() {
		return productCurrency;
	}
	public void setProductCurrency(String productCurrency) {
		this.productCurrency = productCurrency;
	}
	public String getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(String priceChange) {
		this.priceChange = priceChange;
	}
	public String getPriceChangeSign() {
		return priceChangeSign;
	}
	public void setPriceChangeSign(String priceChangeSign) {
		this.priceChangeSign = priceChangeSign;
	}
	
}
