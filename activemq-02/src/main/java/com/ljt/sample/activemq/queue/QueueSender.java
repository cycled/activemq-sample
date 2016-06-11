package com.ljt.sample.activemq.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @Project       : activemq-02
 * @Program Name  : com.ljt.sample.activemq.queue.QueueSender.java
 * @Description   : 通过Spring JMSTemplate完成一个Queue消息发送
 * @Author        : wangchao
 * @Creation Date : 2016年6月12日 上午3:51:14 
 * @ModificationHistory  
 */
@Component
public class QueueSender {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		QueueSender qs = context.getBean(QueueSender.class);
		qs.jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage("spring mq msg");
				return message;
			}
		});
	}
	
}
