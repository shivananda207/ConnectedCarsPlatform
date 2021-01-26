package com.demo.rabitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.rabitmq.MessageFormat;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${demo.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${demo.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(MessageFormat message) {
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
		System.out.println("Send msg = " + message);
	    
	}
}
