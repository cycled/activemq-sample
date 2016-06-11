package com.ljt.sample.activemq.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicSubscriber;

import com.ljt.sample.activemq.core.PersistenceTopicSubscriber;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.topic.PersistenceReceiver.java
 * @Description   : 用于订阅可持久化消息的消费者
 * @Author        : wangchao
 * @Creation Date : 2016年6月11日 下午9:49:44 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月11日        create
 */
public class PersistenceReceiver {
	
	public static void main(String[] args) throws JMSException {
		final String destination = "PersistenceTopic2";
		PersistenceTopicSubscriber subscriber = new PersistenceTopicSubscriber() {
			@Override
			protected void messageHandler(Session session, TopicSubscriber target) throws JMSException {
				Message message = target.receive();
				System.out.println("接收消息来自:" + destination);
				while (message != null) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println("接收消息：" + textMessage.getText());
					message = target.receive(1000L);
				}
			}
		};
		subscriber.setReceiver("c2", "s2");
		subscriber.execute(destination);
	}

}
