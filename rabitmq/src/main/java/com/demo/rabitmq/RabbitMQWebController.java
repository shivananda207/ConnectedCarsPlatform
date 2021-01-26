package com.demo.rabitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	@GetMapping(value = "/producer")
	public String producer(@RequestParam("city") String city,@RequestParam("lidStatus") Boolean lidStatus) {
	
	MessageFormat msg=new MessageFormat();
	msg.setCity(city);
	msg.setLidStatus(lidStatus);
	rabbitMQSender.send(msg);    

		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

}

