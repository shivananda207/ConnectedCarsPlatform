package com.demo.eventtrigger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Configuration
@EnableScheduling
public class EventService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);
	@Value("${rabbitmq.server}")
	private String rabbitmqServer;
	@Value("${rabbitmq.port}")
	private String rabbitmqServerPort;
	
	private Boolean status;
	private String city;
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public void post (Boolean status, String city) throws Exception {
	    if (null!= status && null != city) {
	    	this.setCity(city);
	    	this.setStatus(status);
	    }
		rabbitmqServiceCall();		
	}

	@Scheduled(fixedRate = 120000)
	public void rabbitmqServiceCall() {
		
		if (null==getStatus()) {
			this.setStatus(false);
		} 
		if (null==getCity()) {
			this.setCity("BANGALORE");
		} 
		String url = "http://"+rabbitmqServer+":"+rabbitmqServerPort+"/demo/producer?lidStatus=" + this.getStatus() + "&city=" + this.getCity() ;
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity entity = null;
		try {
			restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		} catch (Exception e) {
			LOGGER.error("Error in calling rabbitmq service >> {}", e);
		}
	}
	
}
