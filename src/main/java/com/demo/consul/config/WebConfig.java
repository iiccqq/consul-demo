package com.demo.consul.config;

import javax.annotation.Resource;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.consul.listener.ConsulListener;
import com.orbitz.consul.AgentClient;

@Configuration
public class WebConfig {

	@Bean
	ServletListenerRegistrationBean<ConsulListener>  conifgListener(){
		ServletListenerRegistrationBean<ConsulListener> srb = new ServletListenerRegistrationBean<ConsulListener>();
		srb.setListener(new ConsulListener(agentClient));
		return srb;
	}
	@Resource
	AgentClient agentClient;
}
