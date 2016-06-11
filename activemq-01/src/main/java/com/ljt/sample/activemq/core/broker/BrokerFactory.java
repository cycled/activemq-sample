package com.ljt.sample.activemq.core.broker;

import org.apache.activemq.broker.BrokerService;

public class BrokerFactory {
	public static void main(String[] args) throws Exception {
		String url = "properties:broker.properties";
		BrokerService service = org.apache.activemq.broker.BrokerFactory.createBroker(url);
		service.addConnector("tcp://localhost:51616");
		service.start();
	}
}
