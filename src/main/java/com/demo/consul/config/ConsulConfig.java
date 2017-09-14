package com.demo.consul.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.KeyValueClient;


@Configuration
public class ConsulConfig {

	@Resource(name="consul")
	Consul consul;
	
	@Bean("agentClient")
	AgentClient getAgentClient(){
		return  consul.agentClient();
	}
	@Bean("keyValueClient")
	KeyValueClient getKeyValueClient(){
		return  consul.keyValueClient();
	}
	@Bean("healthClient")
	HealthClient getHealthClient(){
		return  consul.healthClient();
	}
	@Bean("consul")
	Consul getConsul(){
		Consul consul  = Consul.builder().withHostAndPort(HostAndPort.fromString("192.168.123.128:8500")).build(); 
		return consul;	
	}
}
