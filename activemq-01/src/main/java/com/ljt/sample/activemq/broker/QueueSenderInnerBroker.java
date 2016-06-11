package com.ljt.sample.activemq.broker;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ljt.sample.activemq.core.ProduceQueueComponent;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.broker.QueueSenderInnerBroker.java
 * @Description   : 发送消息至内部启动的broker服务
 * @Author        : wangchao
 * @Creation Date : 2016年6月11日 下午11:59:45 
 */
public class QueueSenderInnerBroker {
	
	public static void main(String[] args) throws JMSException {
		new ProduceQueueComponent("tcp://localhost:51616") {
			@Override
			protected void messageHandler(Session session, MessageProducer target) throws JMSException {
				for (int i = 0 ; i < 3; i++){
					TextMessage message = session.createTextMessage("message:" + i);
					target.send(message);
				}
			}
		}.execute("MyQueueBroker");
	}
	
}
