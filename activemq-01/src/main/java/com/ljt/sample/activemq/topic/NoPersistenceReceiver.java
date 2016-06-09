package com.ljt.sample.activemq.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ljt.sample.activemq.core.ConsumerTopicComponent;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.topic.NoPersistenceReceiver.java
 * @Description   : 订阅一个非持久化的消息
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午5:00:24 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class NoPersistenceReceiver {
	
	public static void main(String[] args) throws JMSException {
		
		ConsumerTopicComponent component = new ConsumerTopicComponent() {
			@Override
			protected void messageHandler(Session session, MessageConsumer target) throws JMSException {
				Message message = target.receive();
				while (message != null) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println("收到消息:" + textMessage.getText());
					message = target.receive(1000L);
				}
			}
		};
		component.execute("myTopic");
	}
	
}
