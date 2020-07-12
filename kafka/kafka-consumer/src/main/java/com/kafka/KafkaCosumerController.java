package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaCosumerController {
	
	@Autowired
	KafkaConsumer1 kafkaConsumer1;
	
	@Autowired
	KafkaConsumer2 kafkaConsumer2;
	
	@GetMapping("/kafka/size1")
	public Integer size1() {
		return kafkaConsumer1.size();
	}
	
	@GetMapping("/kafka/size2")
	public Integer size2() {
		return kafkaConsumer2.size();
	}
}