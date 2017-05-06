package com.zhang.rabbitmq.object;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhang.po.Good;

@Component
public class ObjectSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(Good good) {
		System.out.println("Sender object: " + good.toString());
		this.rabbitTemplate.convertAndSend("object", good);
	}

}