package com.ljt.sample.activemq.core.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @Project       : activemq-01
 * @Program Name  : com.ljt.sample.activemq.core.broker.InnerBroker.java
 * @Description   : 发布一个消息服务
 * @Author        : wangchao
 * @Creation Date : 2016年6月11日 下午11:44:39 
 * @ModificationHistory  
 * Who          When             What 
 * ----------   -------------    -----------------------------------
 * wangchao     2016年6月11日        create
 */
public class InnerBroker {
	
	public static void main(String[] args) throws Exception {
		BrokerService service = new BrokerService();
		service.addConnector("tcp://localhost:51616");
		service.start();
	}
	
}
