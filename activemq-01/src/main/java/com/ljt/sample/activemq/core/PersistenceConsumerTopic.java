package com.ljt.sample.activemq.core;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.ConsumerTopicComponent.java
 * @Description   : 适用于持久化的可以从topic接收订阅消息的消费者组件
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午6:31:35 
 */
public abstract class PersistenceConsumerTopic extends ConsumerTopicComponent {
	
	@Override
	protected void start() throws JMSException {
	}
	
	@Override
	public void crateTarget(Session session, String destinationName) throws JMSException {
		super.crateTarget(session, destinationName);
	}

}
