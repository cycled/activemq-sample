package com.ljt.sample.activemq.core;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.ConsumerTopicComponent.java
 * @Description   : 适用于持久化的可以从topic接收订阅消息的消费者组件<br/>
 * 					注意：当前的消费者组件需要先运行一次，相当于先向消息服务中间件注册，然后再运行发布者组件向消息服务中间件发送消息,<br/>
 * 					此时，无论消费者是否在线，都会接收到生产者发布的消息，消费者不在线的话，下次启动连接的时候，会把没接收过的消息都接收下来。
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午6:31:35 
 */
public abstract class PersistenceTopicSubscriber extends ActiveMQTemplate<TopicSubscriber> implements Component<TopicSubscriber> {
	
	private TopicSubscriber target;
	
	/**
	 * 消费者连接ID
	 */
	private String clientId;
	
	/**
	 * 订阅者ID
	 */
	private String subscriberId;
	
	/**
	 *  @Description	: 设置接收者信息
	 *  @return         : void
	 *  @Creation Date  : 2016年6月11日 下午10:10:16 
	 *  @Author         : wangchao
	 */
	public void setReceiver(String clientId,String subId){
		this.clientId = clientId;
		this.subscriberId = subId;
	}
	
	@Override
	protected void crateConnectionAfter(Connection connection) throws JMSException {
		// 设置连接ID
		if (connection != null) 
			connection.setClientID(this.clientId);
	}
	
	@Override
	protected void start() throws JMSException {
		// 覆盖父类方法,暂缓连接启动操作
	}
	
	/**
	 * 创建并设置订阅信息
	 */
	@Override
	public void crateTarget(Session session, String destinationName) throws JMSException {
		// 创建一个TopicSubscriber 用于订阅
		Topic destination = session.createTopic(destinationName);
		// 需要在连接上设置消费者 ID:t1,用于识别消费者
		this.target = session.createDurableSubscriber(destination, this.subscriberId);
	}
	
	@Override
	protected void targetAfter(Connection connection, Session session) throws JMSException {
		// 完成订阅的设置后，再启动连接
		if( connection != null)
			connection.start();
	}
	
	@Override
	public TopicSubscriber getTarget() {
		return target;
	}
	
	@Override
	public Component<TopicSubscriber> getComponent() {
		return this;
	}

}
