package com.demo.rabitmq;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQListner implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQListner.class);
	@Autowired
	Product product;
	
	private Boolean lidFlag;

	public Boolean getLidFlag() {
		return lidFlag;
	}

	public void setLidFlag(Boolean lidFlag) {
		this.lidFlag = lidFlag;
	}


	public void onMessage(Message message) {
		
		String receivedMessage = "";
		receivedMessage = new String(message.getBody());
		try {
			Map<String, Object> response = new ObjectMapper().readValue(receivedMessage, HashMap.class);
			
			String carLocation = response.get("city").toString();
			String fuelTankLidStatus= response.get("lidStatus").toString();
			LOGGER.info("===========================================================");
			LOGGER.info("===========================================================");
			LOGGER.info("Vehicle is in city {} and fueltank lid status is {} \n",carLocation, fuelTankLidStatus);
			
			LOGGER.info("===========================================================");
			LOGGER.info("===========================================================");
				
				getFuelConsumedData(carLocation,fuelTankLidStatus);			
		
		} catch (JsonProcessingException e) {
			LOGGER.error("Error in extracting message  >> {}", e);
		}
	
		System.out.println("Consuming Message - " + new String(message.getBody()));
	}
	
	public void getFuelConsumedData (String city, String status) {
		
		String url = "http://fuelprice-api-india.herokuapp.com/price/";
		String state = "";
		switch(city) {
		case "BANGALORE":  state="Karnataka";
			break;
		case "MUMBAI":  state="Maharashtra";
		break;
		case "DELHI":  state="Delhi";
		break;
		case "KOLKATA":  state="West-Bengal";
		break;
		case "HYDERABAD":  state="Telangana";
		break;
		case "CHENNAI":  state="Tamil-Nadu";
		break;
		case "KOCHI":  state="Kerala";
		break;
		default:
		   state="Karnataka";
		}
		if (state.equalsIgnoreCase("Delhi")){
				city = "New Delhi";
		}
		String requestUrl = url+state+"/"+city;
		
		LOGGER.info("URL to request fuel price is >>> {}",requestUrl);
		RestTemplate restTemplate = new RestTemplate();
		Map<String,String> map = new HashMap<>();
		HttpEntity entity = null;	
		HttpHeaders httpHeaders = getHttpHeader(map);
		entity = new HttpEntity(httpHeaders);
		LOGGER.error("Header value is>> {}", entity);
		try {
			ResponseEntity<String> response =null;
			response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);
			System.out.println(response);
			
			//Mocking fuel price value as the JSON from API is unable to PARSE
			String price = "88";
			int fuelPrice = Integer.parseInt(price);
			
		
		} catch (Exception e) {
			LOGGER.error("Error in getting fueldata  >> {}", e);
		}
	}
	
	public HttpHeaders getHttpHeader(Map<String, String> map){
		HttpHeaders header = new HttpHeaders();
		map.put("Accept", "application/json");
		map.put("Content-Type", "application/json");
		Set<String> keys = map.keySet();
        for(String key : keys) {
            if(!header.containsKey(key))
            	header.add(key, map.get(key));
        }
		return header;
	}
}
