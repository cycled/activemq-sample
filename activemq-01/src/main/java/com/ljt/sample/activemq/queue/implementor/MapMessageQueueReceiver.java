package com.ljt.sample.activemq.queue.implementor;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.ljt.sample.activemq.queue.AbstractQueue;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.implementor.MapMessageQueueReceiver.java
 * @Description   : 创建一个MapMessage类型的消息队列接收者
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午2:57:43 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class MapMessageQueueReceiver {
	
	public static void main(String[] args) throws JMSException {
		
		AbstractQueue queue = new AbstractQueue() {
			
			@Override
			protected void createDestination(Session session) throws JMSException {
				Destination destination = session.createQueue("my-queue");
				MessageConsumer consumer = session.createConsumer(destination);
				
				for (int i = 0; i < 3; i++){
					MapMessage message = (MapMessage) consumer.receive();
					session.commit();
					System.out.println("收到消息： " + message.getStringProperty("status") + 
									   ", property = " + message.getStringProperty("msg-" + i));
				}
				
			}
		};
		
		queue.execute();
		
	}

}
