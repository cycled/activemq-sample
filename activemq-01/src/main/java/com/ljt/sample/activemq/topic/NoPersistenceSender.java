package com.ljt.sample.activemq.topic;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ljt.sample.activemq.core.ProduceTopicComponent;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.topic.NoPersistenceSender.java
 * @Description   : 发布一个非持久化的消息
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午4:48:40 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class NoPersistenceSender {

	public static void main(String[] args) throws JMSException {
		ProduceTopicComponent component = new ProduceTopicComponent() {
			@Override
			protected void messageHandler(Session session, MessageProducer target) throws JMSException {
				for (int i = 0; i < 3; i++){
					TextMessage message = session.createTextMessage("msg-" + i);
					target.send(message);
				}
			}
		};
		component.execute("myTopic");
	}
	
}
