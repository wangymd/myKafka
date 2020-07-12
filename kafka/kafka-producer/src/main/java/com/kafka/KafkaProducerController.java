package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/kafka/send1/{size}/{data}")
	public String send1(@PathVariable("size") Integer size, @PathVariable("data") String data) {
		for (int i = 1; i <= size; i++) {
			kafkaTemplate.send("test", data + "-" + i);
		}
		return "ok";
	}
	
	@GetMapping("/kafka/send2/{data}")
	public String send2(@PathVariable("data") String data) {
		for (int i = 0; i < 100; i++) {
			ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("test", data + "-" + i);
			listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

				@Override
				public void onSuccess(SendResult<String, String> result) {
					System.out.println("onSuccess:" + result);
					System.out.println("onSuccess getProducerRecord partition:" + result.getProducerRecord().partition());
					System.out.println("onSuccess getRecordMetadata offset:" + result.getRecordMetadata().offset());
				}

				@Override
				public void onFailure(Throwable ex) {
					System.out.println("onFailure:" + ex.getMessage());
				}
			});
		}
		return "ok";
	}
	
	@GetMapping("/kafka/send3/{data}")
	public String send3(@PathVariable("data") String data) {
		for (int i = 0; i < 100; i++) {
			kafkaTemplate.send("test", "data", data + "-" + i);
		}
		return "ok";
	}
	
	@GetMapping("/kafka/send4/{data}")
	public String send4(@PathVariable("data") String data) {
		for (int i = 0; i < 100; i++) {
			kafkaTemplate.send("test", 0, "data", data + "-" + i);
		}
		return "ok";
	}
	
}