package com.ljt.sample.activemq.topic;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ljt.sample.activemq.core.PersistenceProductTopic;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.topic.PersistenceSender.java
 * @Description   : 用于发布可持久化的消息生产者
 * @Author        : wangchao
 * @Creation Date : 2016年6月11日 下午9:46:46 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月11日        create
 */
public class PersistenceSender {
	
	public static void main(String[] args) throws JMSException {
		final String destination = "PersistenceTopic2";
		PersistenceProductTopic productTopic = new PersistenceProductTopic() {
			@Override
			protected void messageHandler(Session session, MessageProducer target) throws JMSException {
				System.out.println("发布消息至:" + destination);
				// 向topic发布三个消息
				for (int i = 0; i < 3; i++){
					TextMessage message = session.createTextMessage("msg-" + i);
					target.send(message);
				}
			}
		};
		productTopic.execute(destination);
	}
	
}
