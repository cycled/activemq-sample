package com.ljt.sample.activemq.queue;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.ljt.sample.activemq.AbstractMQTemplate;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.MapMessageQueueSender.java
 * @Description   : 创建一个MapMessage类型的消息队列
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午1:29:14 
 */
public class MapMessageQueueSender {
	
	public static void main(String[] args) throws JMSException {

		AbstractMQTemplate queue = new AbstractMQTemplate() {
			@Override
			protected void createDestination(Session session) throws JMSException {
				// 创建一个消息队列及其生产者
				Destination destination = session.createQueue("my-queue");
				MessageProducer producer = session.createProducer(destination);
				// 创建MapMessage消息
				for (int i = 0 ; i < 3; i++){
					MapMessage message = session.createMapMessage();
					message.setStringProperty("status", "ok");
					message.setStringProperty("msg-" + i, "my map msg : " + i);
					producer.send(message);
				}
				session.commit();
			}
		};
		
		queue.execute();
	}

}
