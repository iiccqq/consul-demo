package com.demo.consul.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.orbitz.consul.AgentClient;

public class ConsulListener implements ServletContextListener {

	AgentClient agentClient;
	Thread consulThread;
	boolean isStop = false;

	public ConsulListener(AgentClient agentClient) {
		this.agentClient = agentClient;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		String serviceName = "consul_demo_service";
		String serviceId = "1";
		agentClient.register(8080, 3L, serviceName, serviceId);
		try {
			agentClient.pass(serviceId);
			consulThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (!isStop) {
							agentClient.pass(serviceId);
							System.out.println("live");
							Thread.sleep(1000L);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			consulThread.start();
		} catch (Exception e) {

		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		isStop = true;
	}

}
