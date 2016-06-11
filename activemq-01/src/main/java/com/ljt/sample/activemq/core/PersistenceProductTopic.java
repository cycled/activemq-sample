package com.ljt.sample.activemq.core;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.core.PersistenceProductTopic.java
 * @Description   : 适用于持久化的可以发布到topic的消息生产者组件
 * @Author        : wangchao
 * @Creation Date : 2016年6月11日 下午9:09:52 
 */
public abstract class PersistenceProductTopic extends ProduceTopicComponent {
	
	// 覆盖父类启动方法，延迟连接启动时机
	@Override
	protected void start() throws JMSException {
	}
	
	@Override
	public void crateTarget(Session session, String destinationName) throws JMSException {
		super.crateTarget(session, destinationName);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	}
	
	@Override
	protected void targetAfter(Connection connection, Session session) throws JMSException {
		// 在此处执行连接启动
		super.start();
	}

}
