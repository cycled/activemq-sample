package com.ljt.sample.activemq.queue;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.queue.AbstractQueue.java
 * @Description   : 消息队列模板
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午3:38:07 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public abstract class AbstractQueue {

	protected Connection conn;
	protected Session session;

	public AbstractQueue() {
	}

	public void execute() throws JMSException {

		try {
			conn = new ActiveMQConnectionFactory("tcp://171.16.1.230:61616").createConnection();
			conn.start();
			Enumeration<?> names = conn.getMetaData().getJMSXPropertyNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				System.out.println("jms name = " + name);
			}
			// CLIENT_ACKNOWLEDGE：当客户通过调用消息的acknowledge方法确认消息。
			// 需要注意的是，在这种模式中，确认是在会话层进行的
			session = createSession(conn);
			createDestination(session);
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
	 * @Description : 创建session,默认开启会话事务,并使用AUTO_ACKNOWLEDGE模式创建会话
	 * @return : Session
	 * @Creation Date : 2016年6月10日 上午3:31:17
	 * @Author : wangchao
	 */
	protected Session createSession(Connection conn) throws JMSException {
		// Session.AUTO_ACKNOWLEDGE
		// 当客户成功的从receive方法返回的时候，或者从MessageListener.onMessage方法
		// 返回的时候，会话自动确认客户收到的消息。
		return conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	}

	protected abstract void createDestination(Session session) throws JMSException;

}
