package com.ljt.sample.activemq.core;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.ConsumerTopicComponent.java
 * @Description   : 这是一个可以从queue接收消息的消费者组件
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午6:31:35 
 */
public abstract class ConsumerQueueComponent extends ActiveMQTemplate<MessageConsumer> implements Component<MessageConsumer> {
	
	private MessageConsumer consumer;
	
	public ConsumerQueueComponent() {
	}
	
	public ConsumerQueueComponent(String url) {
		super(url);
	}

	@Override
	public void crateTarget(Session session,String destinationName) throws JMSException {
		this.consumer = session.createConsumer(session.createQueue(destinationName));
	}

	@Override
	public MessageConsumer getTarget() {
		return this.consumer;
	}
	
	@Override
	public Component<MessageConsumer> getComponent() {
		return this;
	}


}
