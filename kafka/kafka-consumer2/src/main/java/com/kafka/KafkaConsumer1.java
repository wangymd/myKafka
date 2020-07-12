package com.kafka;

import java.util.HashSet;
import java.util.Set;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer1 {
	
	private Set<String> db = new HashSet<String>();
	
	/**
	 * id要唯一
	 * @param data
	 */
//	@KafkaListener(id = "test1", topics = "test", groupId = "myGroup1")
	@KafkaListener(id = "test1", topics = "test", groupId = "myGroup2")
    public void listen1(String data) {
		db.add(data);
        System.out.println("KafkaConsumer1:" + data + ",db size is "  + db.size());
    }
	
	public Integer size() {
		return db.size();
	}

}
