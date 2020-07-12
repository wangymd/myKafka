package com.kafka;

import java.util.HashSet;
import java.util.Set;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer2 {
	
	private Set<String> db = new HashSet<String>();
	
//	@KafkaListener(id = "test2", topics = "test", groupId = "myGroup2")
	@KafkaListener(id = "test2", topics = "test", groupId = "myGroup2")
    public void listen2(String data) {
		db.add(data);
        System.out.println("KafkaConsumer2:" + data + ",db size is "  + db.size());
    }
	
	public Integer size() {
		return db.size();
	}

}
