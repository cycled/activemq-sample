package com.ljt.sample.activemq.queue.implementor;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.ljt.sample.activemq.queue.AbstractQueue;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.implementor.ClientAcknowledgeQueueReceiver.java
 * @Description   : 非事务的客户消息确认模式的队列接收者
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午3:39:05 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class ClientAcknowledgeQueueReceiver {
	
	public static void main(String[] args) throws JMSException {
		
		AbstractQueue queue = new AbstractQueue() {
			
			@Override
			protected Session createSession(Connection conn) throws JMSException {
				// 创建一个非事务的,客户确认消息模式的会话,CLIENT_ACKNOWLEDGE仅在非事务的会话中生效
				return conn.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
			}
			
			@Override
			protected void createDestination(Session session) throws JMSException {
				Destination destination = session.createQueue("my-queue");
				MessageConsumer consumer = session.createConsumer(destination);
				
				for (int i = 0; i < 3; i++) {
					System.out.println("当前接收消息次序为:" + (i + 1));
					
					MapMessage message = (MapMessage) consumer.receive();
					
					// 在非事务会话中，不需要提交动作
					// 对于CLIENT_ACKNOWLEDGE模式的会话,当一个消息被确认,则自动确认当前会话中的所有消息。
					if(i == 2){
						System.out.println("消息被消费者确认");
						message.acknowledge();
					}
					System.out.println("收到消息： " + message.getStringProperty("status") + 
							   ", property = " + message.getStringProperty("msg-" + i));
				}
			}
		};
		
		queue.execute();
	}
	
}
