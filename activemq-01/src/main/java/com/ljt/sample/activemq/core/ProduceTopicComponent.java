package com.ljt.sample.activemq.core;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.ProduceTopicComponent.java
 * @Description   : 这是一个向topic目标发送消息的生产者组件
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午6:29:06 
 */
public abstract class ProduceTopicComponent extends ActiveMQTemplate<MessageProducer> implements Component<MessageProducer> {
	
	protected MessageProducer producer;

	@Override
	public void crateTarget(Session session,String destinationName) throws JMSException {
		producer = session.createProducer(session.createTopic(destinationName));
	}

	@Override
	public MessageProducer getTarget() {
		return producer;
	}
	
	@Override
	public Component<MessageProducer> getComponent() {
		return this;
	}


}
