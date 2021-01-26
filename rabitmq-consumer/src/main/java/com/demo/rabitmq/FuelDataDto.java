package com.demo.rabitmq;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FuelDataDto {
	public List<FuelData> fuelData;

	public List<FuelData> getFuelData() {
		return fuelData;
	}

	public void setFuelData(List<FuelData> fuelData) {
		this.fuelData = fuelData;
	}
}
