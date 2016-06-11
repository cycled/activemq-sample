package com.ljt.sample.activemq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {
	
	@Autowired
	JmsTemplate template;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		QueueReceiver receiver = context.getBean(QueueReceiver.class);
		String message = (String) receiver.template.receiveAndConvert();
		System.out.println("接到消息：" + message);
	}
	
}
