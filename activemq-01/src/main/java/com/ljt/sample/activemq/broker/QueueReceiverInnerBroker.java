package com.ljt.sample.activemq.broker;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ljt.sample.activemq.core.ConsumerQueueComponent;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.broker.QueueReceiverInnerBroker.java
 * @Description   : 从内部broker服务接收消息
 * @Author        : wangchao
 * @Creation Date : 2016年6月12日 上午12:09:57 
 */
public class QueueReceiverInnerBroker {
	public static void main(String[] args) throws JMSException {
		new ConsumerQueueComponent("tcp://localhost:51616") {
			@Override
			protected void messageHandler(Session session, MessageConsumer target) throws JMSException {
				// 接收来自 队列的消息
				for (int i = 0 ; i < 3; i++){
					TextMessage message = (TextMessage) target.receive();
					session.commit();
					System.out.println("从内部broker服务收到队列消息:" + message.getText());
				}
			}
		}.execute("MyQueueBroker");
	}
}
