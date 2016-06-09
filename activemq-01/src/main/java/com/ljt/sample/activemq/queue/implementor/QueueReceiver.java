package com.ljt.sample.activemq.queue.implementor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.QueueReceiver.java
 * @Description   : 队列接收者
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午12:45:09 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class QueueReceiver {
	
	public static void main(String[] args) throws JMSException {
		// 创建一个连接
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://171.16.1.230:61616");
		Connection conn = factory.createConnection();
		// 启动一个连接
		conn.start();
		// 创建一个会话
		Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		// 创建一个队列
		Destination destination = session.createQueue("my-queue");
		// 创建一个消费者
		MessageConsumer consumer = session.createConsumer(destination);
		// 接收来自 队列的消息
		for (int i = 0 ; i < 3; i++){
			TextMessage message = (TextMessage) consumer.receive();
			session.commit();
			System.out.println("收到消息:" + message.getText());
		}
		// 关闭资源
		session.close();
		conn.close();
	}
	
}
