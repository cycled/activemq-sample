package com.ljt.sample.activemq.core;

import javax.jms.JMSException;
import javax.jms.Session;

public interface Component<T> {
	
	/**
	 *  @Description	: 创建目标对象
	 *  @return         : void
	 *  @Creation Date  : 2016年6月10日 上午7:11:35 
	 *  @Author         : wangchao
	 */
	void crateTarget(Session session,String destinationName) throws JMSException;
	
	/**
	 *  @Description	: 或者目标对象
	 *  @return         : T
	 *  @Creation Date  : 2016年6月10日 上午7:12:12 
	 *  @Author         : wangchao
	 */
	T getTarget();
	
}
