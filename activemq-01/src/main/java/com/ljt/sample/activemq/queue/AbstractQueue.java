package com.ljt.sample.activemq.queue;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

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
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
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
	
	protected abstract void createDestination(Session session) throws JMSException;

}
