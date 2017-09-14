package com.demo.consul;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.health.ServiceHealth;
import com.orbitz.consul.model.kv.Value;

@RestController
public class ConsulController {

	@Resource
	KeyValueClient keyValueClient;
	@Resource
	HealthClient healthClient;

	@GetMapping(value = "add/kv/keys/{key}/values/{value}")
	public Map<String, String> add(@PathVariable String key, @PathVariable String value) {
		keyValueClient.putValue(key, value);
		Map<String, String> m = new HashMap<String, String>();
		m.put("code", "suc");
		return m;
	}

	@GetMapping(value = "get/kv/keys/{key}")
	public Map<String, String> get(@PathVariable String key) {
		Optional<Value> s = keyValueClient.getValue(key);
		Map<String, String> m = new HashMap<String, String>();	
		m.put("code",s.get().getValueAsString().get());
		return m;
	}

	@GetMapping(value = "del/kv/keys/{key}")
	public Map<String, String> del(@PathVariable String key) {
		keyValueClient.deleteKey(key);
		Map<String, String> m = new HashMap<String, String>();
		m.put("code", "suc");
		return m;
	}

	@GetMapping(value = "available")
	public Map<String, Object> available() {
		List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances("consul_demo_service").getResponse();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("code", "suc");
		m.put("nodes", nodes);
		return m;
	}
}
