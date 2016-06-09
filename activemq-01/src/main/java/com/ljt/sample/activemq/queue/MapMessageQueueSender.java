package com.ljt.sample.activemq.queue;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.ljt.sample.activemq.core.ProduceQueueComponent;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.MapMessageQueueSender.java
 * @Description   : 创建一个MapMessage类型的消息队列
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午1:29:14 
 */
public class MapMessageQueueSender {
	
	public static void main(String[] args) throws JMSException {
		ProduceQueueComponent component = new ProduceQueueComponent() {
			@Override
			protected void messageHandler(Session session, MessageProducer target) throws JMSException {
				// 创建MapMessage消息
				for (int i = 0 ; i < 3; i++){
					MapMessage message = session.createMapMessage();
					message.setStringProperty("status", "ok");
					message.setStringProperty("msg-" + i, "my map msg : " + i);
					target.send(message);
				}
			}
		};
		
		component.execute("my-queue");
		
	}

}
