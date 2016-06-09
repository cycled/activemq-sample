package com.ljt.sample.activemq.topic;

import javax.jms.JMSException;
import javax.jms.Session;

import com.ljt.sample.activemq.AbstractMQTemplate;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.topic.NoPersistenceSender.java
 * @Description   : 发布一个非持久化的消息
 * @Author        : wangchao
 * @Creation Date : 2016年6月10日 上午4:48:40 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月10日        create
 */
public class NoPersistenceSender {

	public static void main(String[] args) throws JMSException {
		
		AbstractMQTemplate queue = new AbstractMQTemplate() {
			
			@Override
			protected void createDestination(Session session) throws JMSException {
				
			}
		};
		
		queue.execute();
	}
	
}
