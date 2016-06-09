package com.ljt.sample.activemq.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.ljt.sample.activemq.AbstractQueue;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.implementor.ClientAcknowledgeQueueSender.java
 * @Description   : 非事务的客户消息确认模式的队列发送者
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午3:28:44 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class ClientAcknowledgeQueueSender {
		
	
	public static void main(String[] args) throws JMSException {
		
		AbstractQueue queue = new AbstractQueue() {
			
			// 不开启事务，并使用客户主动确认模式创建session
			@Override
			protected Session createSession(Connection conn) throws JMSException {
				// CLIENT_ACKNOWLEDGE 会话模式仅在非事务的会话中生效
				return conn.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
			}
			
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
				// 非事务的会话，不需要提交动作
			}
		};
		
		queue.execute();
	}
	
}
