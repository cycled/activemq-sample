package com.ljt.sample.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.implementor.QueueSender.java
 * @Description   : 创建一个TextMessage类型的消息队列，并发送给provider
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午1:31:50 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class QueueSender {
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		// 创建一个连接
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://171.16.1.230:61616");
		Connection conn = factory.createConnection();
		conn.start();
		// 创建一个会话
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建一个队列
		Destination destination = session.createQueue("my-queue");
		// 创建一个生产者
		MessageProducer producer = session.createProducer(destination);
		// 创建三个 TextMessage消息
		for (int i = 0 ; i < 3; i++){
			TextMessage message = session.createTextMessage("message:" + i);
			Thread.sleep(1000);
			producer.send(message);
		}
		// 关闭连接
		session.commit();
		session.close();
		conn.close();
	}

}
