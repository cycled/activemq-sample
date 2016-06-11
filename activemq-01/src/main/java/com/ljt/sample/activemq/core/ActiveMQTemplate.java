package com.ljt.sample.activemq.core;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Project : activemq-01
 * @Program Name : com.ljt.sample.activemq.queue.AbstractQueue.java
 * @Description : 消息队列模板
 * @Author : wangchao
 * @Creation Date : 2016年6月10日 上午3:38:07
 * @ModificationHistory Who When What ---------- -------------
 *                      ----------------------------------- wangchao 2016年6月10日
 *                      create
 */
public abstract class ActiveMQTemplate<T> {

	private Connection conn;

	private Session session;

	private boolean transactionActivated = false;

	public ActiveMQTemplate() {
	}

	/**
	 *  @Description	: 创建一个连接
	 *  @return         : void
	 *  @Creation Date  : 2016年6月10日 上午8:32:15 
	 *  @Author         : wangchao
	 */
	protected void crateConnection() throws JMSException {
		this.conn = new ActiveMQConnectionFactory("tcp://171.16.1.230:61616").createConnection();
	}
	
	/**
	 *  @Description	: 启动连接
	 *  @return         : void
	 *  @Creation Date  : 2016年6月10日 上午8:32:00 
	 *  @Author         : wangchao
	 */
	protected void start() throws JMSException {
		this.conn.start();
	}
	
	protected void setConn(Connection conn) {
		this.conn = conn;
	}

	protected Connection getConn() {
		return conn;
	}

	/**
	 * @Description : 执行消息的创建或者接收
	 * @return : void
	 * @Creation Date : 2016年6月10日 上午6:50:10
	 * @Author : wangchao
	 */
	public void execute(String destinationName) throws JMSException {

		try {

			crateConnection();
			
			start();
			
			Enumeration<?> names = conn.getMetaData().getJMSXPropertyNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				System.out.println("jms name = " + name);
			}
			// CLIENT_ACKNOWLEDGE：当客户通过调用消息的acknowledge方法确认消息。
			// 需要注意的是，在这种模式中，确认是在会话层进行的
			session = createSession(conn);

			getComponent().crateTarget(session, destinationName);

			targetAfter(conn, session);

			messageHandler(session, getComponent().getTarget());

			messageHandlerAfter(conn, session);

			// 如果事务被激活,则进行提交动作
			if (transactionActivated)
				session.commit();

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} finally {
				conn.close();
			}
		}
	}

	/**
	 * @Description : 消息执行后处理动作
	 * @return : void
	 * @Creation Date : 2016年6月10日 上午8:23:58
	 * @Author : wangchao
	 */
	protected void messageHandlerAfter(Connection connection, Session session) {

	}

	/**
	 * @param session
	 * @Description : 目标后处理方法
	 * @return : void
	 * @throws JMSException 
	 * @Creation Date : 2016年6月10日 上午8:21:17
	 * @Author : wangchao
	 */
	protected void targetAfter(Connection connection, Session session) throws JMSException {

	}

	/**
	 * @Description : 或者当前组件
	 * @return : Component<T>
	 * @Creation Date : 2016年6月10日 上午7:12:57
	 * @Author : wangchao
	 */
	public abstract Component<T> getComponent();

	/**
	 * @Description : 对消息进行处理
	 * @return : void
	 * @Creation Date : 2016年6月10日 上午6:48:41
	 * @Author : wangchao
	 */
	protected abstract void messageHandler(Session session, T target) throws JMSException;

	/**
	 * @Description : 设置事务是否被激活
	 * @return : void
	 * @Creation Date : 2016年6月10日 上午6:49:40
	 * @Author : wangchao
	 */
	protected void setTransactionActivated(boolean transactionActivated) {
		this.transactionActivated = transactionActivated;
	}

	/**
	 * @Description : 创建session,默认开启会话事务,并使用AUTO_ACKNOWLEDGE模式创建会话
	 * @return : Session
	 * @Creation Date : 2016年6月10日 上午3:31:17
	 * @Author : wangchao
	 */
	protected Session createSession(Connection conn) throws JMSException {
		// Session.AUTO_ACKNOWLEDGE
		// 当客户成功的从receive方法返回的时候，或者从MessageListener.onMessage方法
		// 返回的时候，会话自动确认客户收到的消息。
		transactionActivated = true;
		return conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	}

}
